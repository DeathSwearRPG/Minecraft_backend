package com.minecraft_wiki.backend.Model.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    private UUID id;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(nullable = false)
    private String status;

    @Column(name = "amount_value", precision = 10, scale = 2)
    private BigDecimal amountValue;

    @Column(nullable = false)
    private String currency;

    @Column(name = "yookassa_payment_id")
    private String yookassaPaymentId;

    @Column(name = "confirmation_url")
    private String confirmationUrl;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "paid_at")
    private Instant paidAt;
}
