package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.Product;
import com.minecraft_wiki.backend.Model.Purchase;
import com.minecraft_wiki.backend.Model.UserProfile;
import com.minecraft_wiki.backend.Repo.UserProfileRepository;
import com.minecraft_wiki.backend.Service.CurrentUserService;
import com.minecraft_wiki.backend.Service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final CurrentUserService currentUserService;

    @GetMapping
    public List<Product> getProducts() {
        return shopService.getAllProducts();
    }

    @PostMapping("/purchase/{productId}")
    public Map<String, String> doPurchase(
            @PathVariable Long productId,
            @AuthenticationPrincipal OidcUser user
    ) {
        String confirmationUrl = shopService.createPayment(productId, user);

        return Map.of("confirmationUrl", confirmationUrl);
    }

    @PostMapping("/yookassa/webhook")
    public void handleYookassaWebhook(@RequestBody Map<String, Object> payload) {
        shopService.handleYookassaWebhook(payload);
    }
}
