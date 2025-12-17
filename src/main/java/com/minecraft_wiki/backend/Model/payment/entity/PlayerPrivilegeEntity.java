package com.minecraft_wiki.backend.Model.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "player_privileges")
public class PlayerPrivilegeEntity {

    @Id
    private UUID id;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "privilege_code")
    private String privilegeCode;

    @Column(name = "starts_at")
    private Instant startsAt;

    @Column(name = "ends_at")
    private Instant endsAt;

    private String status;

    @Column(name = "source_payment_id")
    private UUID sourcePaymentId;
}
