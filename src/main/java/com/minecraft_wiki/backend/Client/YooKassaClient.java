package com.minecraft_wiki.backend.Client;

import com.minecraft_wiki.backend.Model.payment.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Component
public class YooKassaClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String shopId;
    private final String secretKey;

    public YooKassaClient(
            @Value("${yookassa.shop-id}") String shopId,
            @Value("${yookassa.secret-key}") String secretKey
    ) {
        this.shopId = shopId;
        this.secretKey = secretKey;
    }

    public PaymentResponse createPayment(
            UUID paymentId,
            String productCode,
            String playerName,
            BigDecimal amount,
            String currency
    ) {

        Map<String, Object> payload = Map.of(
                "amount", Map.of(
                        "value", amount.toPlainString(),
                        "currency", currency
                ),
                "capture", true,
                "confirmation", Map.of(
                        "type", "redirect",
                        "return_url", "https://example.com/success"
                ),
                "description", "Minecraft privilege: " + productCode,
                "metadata", Map.of(
                        "paymentId", paymentId.toString(),
                        "productCode", productCode,
                        "playerName", playerName
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(shopId, secretKey);
        headers.add("Idempotence-Key", paymentId.toString());

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://api.yookassa.ru/v3/payments",
                HttpMethod.POST,
                entity,
                Map.class
        );

        Map body = response.getBody();
        Map confirmation = (Map) body.get("confirmation");

        return new PaymentResponse(
                (String) body.get("id"),
                (String) body.get("status"),
                confirmation != null
                        ? (String) confirmation.get("confirmation_url")
                        : null
        );
    }
}
