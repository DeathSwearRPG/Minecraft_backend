package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Service.YooKassaWebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/yookassa")
public class YooKassaWebhookController {

    private final YooKassaWebhookService webhookService;

    public YooKassaWebhookController(YooKassaWebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> handle(@RequestBody Map<String, Object> payload) {
        webhookService.handle(payload);
        return ResponseEntity.ok().build();
    }
}
