package com.minecraft_wiki.backend.Model.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private String code;

    private String name;

    @Column(name = "price_value", precision = 10, scale = 2)
    private BigDecimal priceValue;

    @Column(nullable = false)
    private String currency;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    @Column(name = "privilege_code", nullable = false)
    private String privilegeCode;

    @Column(name = "is_active")
    private boolean active = true;
}
