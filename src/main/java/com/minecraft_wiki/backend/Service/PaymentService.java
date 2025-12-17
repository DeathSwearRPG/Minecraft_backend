package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Client.YooKassaClient;
import com.minecraft_wiki.backend.Model.payment.CreatePaymentRequest;
import com.minecraft_wiki.backend.Model.payment.PaymentResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    private final YooKassaClient yooKassaClient;

    // ВРЕМЕННО каталог продуктов позже уйдёт в БД
    private static final Map<String, ProductConfig> PRODUCTS = Map.of(

            //  УРОВЕНЬ 1 
            "BLESSED_30D", new ProductConfig(
                    new BigDecimal("150.00"),
                    "RUB",
                    30,
                    "blessed"
            ),

            //  УРОВЕНЬ 2 
            "CHOSEN_LIGHT_30D", new ProductConfig(
                    new BigDecimal("250.00"),
                    "RUB",
                    30,
                    "chosen_light"
            ),
            "CHOSEN_DARK_30D", new ProductConfig(
                    new BigDecimal("250.00"),
                    "RUB",
                    30,
                    "chosen_dark"
            ),
            "CHOSEN_WAR_30D", new ProductConfig(
                    new BigDecimal("250.00"),
                    "RUB",
                    30,
                    "chosen_war"
            ),

            //  УРОВЕНЬ 3 
            "OATH_CHOSEN_30D", new ProductConfig(
                    new BigDecimal("350.00"),
                    "RUB",
                    30,
                    "oath_chosen"
            )
    );



    public PaymentService(YooKassaClient yooKassaClient) {
        this.yooKassaClient = yooKassaClient;
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {

        ProductConfig product = PRODUCTS.get(request.getProductCode());
        if (product == null) {
            throw new IllegalArgumentException("Unknown productCode: " + request.getProductCode());
        }

        UUID paymentId = UUID.randomUUID();

        return yooKassaClient.createPayment(
                paymentId,
                request.getProductCode(),
                request.getPlayerName(),
                product.price(),
                product.currency()
        );
    }

    // Внутренний record — НЕ DTO
    private record ProductConfig(
            BigDecimal price,
            String currency,
            int durationDays,
            String privilegeCode
    ) {}
}
