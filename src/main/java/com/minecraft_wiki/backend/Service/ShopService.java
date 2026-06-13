package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.Product;
import com.minecraft_wiki.backend.Model.Purchase;
import com.minecraft_wiki.backend.Model.UserProfile;
import com.minecraft_wiki.backend.Model.enums.PurchaseStatus;
import com.minecraft_wiki.backend.Repo.ProductRepository;
import com.minecraft_wiki.backend.Repo.PurchaseRepository;
import com.minecraft_wiki.backend.Repo.UserProfileRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ShopService {
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserProfileRepository userProfileRepository;
    private final CurrentUserService currentUserService;
    private final MinecraftRconService minecraftRconService;

    @Value("${yookassa.shop-id}")
    private String shopId;

    @Value("${yookassa.secret-key}")
    private String secretKey;

    @Value("${yookassa.return-url}")
    private String returnUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Product> getAllProducts() {
        return productRepository.findByActiveTrue();
    }

    public String createPayment(Long productId, OidcUser user) {
        UserProfile profile = currentUserService.getOrCreateProfile(user);

        if (profile.getMinecraftNickname() == null || profile.getMinecraftNickname().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Minecraft nickname is required before purchase"
            );
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        if (!product.getActive()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product is not active"
            );
        }

        Purchase purchase = Purchase.builder()
                .userProfileId(profile.getId())
                .productId(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .createdAt(LocalDateTime.now())
                .status(PurchaseStatus.CREATED)
                .build();

        purchase = purchaseRepository.save(purchase);

        Map<String, Object> response = createYookassaPayment(product, purchase);

        String paymentId = (String) response.get("id");

        Map<String, Object> confirmation = (Map<String, Object>) response.get("confirmation");
        String confirmationUrl = (String) confirmation.get("confirmation_url");

        purchase.setTransactionId(paymentId);
        purchase.setStatus(PurchaseStatus.IN_PROGRESS);
        purchaseRepository.save(purchase);

        return confirmationUrl;
    }

    private Map<String, Object> createYookassaPayment(Product product, Purchase purchase) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(shopId, secretKey);
        headers.set("Idempotence-Key", UUID.randomUUID().toString());

        Map<String, Object> body = Map.of(
                "amount", Map.of(
                        "value", formatAmount(product.getPrice()),
                        "currency", "RUB"
                ),
                "capture", true,
                "description", "Purchase #" + purchase.getId() + ": " + product.getProductName(),
                "metadata", Map.of(
                        "purchaseId", purchase.getId().toString()
                ),
                "confirmation", Map.of(
                        "type", "redirect",
                        "return_url", returnUrl
                )
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://api.yookassa.ru/v3/payments",
                HttpMethod.POST,
                entity,
                Map.class
        );

        return response.getBody();
    }

    public void handleYookassaWebhook(Map<String, Object> payload) {
        String event = (String) payload.get("event");

        Map<String, Object> object = (Map<String, Object>) payload.get("object");
        String paymentId = (String) object.get("id");

        Purchase purchase = purchaseRepository.findByTransactionId(paymentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Purchase not found"
                ));

        if ("payment.succeeded".equals(event)) {
            handlePaymentSucceeded(purchase);
            return;
        }

        if ("payment.canceled".equals(event)) {
            purchase.setStatus(PurchaseStatus.FAILED);
            purchaseRepository.save(purchase);
        }
    }

    private void handlePaymentSucceeded(Purchase purchase) {
        if (purchase.getStatus() == PurchaseStatus.SUCCESS) {
            return;
        }

        UserProfile profile = userProfileRepository.findById(purchase.getUserProfileId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User profile not found"
                ));

        Product product = productRepository.findById(purchase.getProductId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        String command = product.getCommandToGrant();

        if (command == null || command.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product command is not configured"
            );
        }

        String finalCommand = command.replace("{nickname}", profile.getMinecraftNickname());

        minecraftRconService.sendCommand(finalCommand);

        purchase.setStatus(PurchaseStatus.SUCCESS);
        purchaseRepository.save(purchase);
    }

    private String formatAmount(BigDecimal amount) {
        return amount.setScale(2).toPlainString();
    }
}
