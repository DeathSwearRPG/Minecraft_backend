package com.minecraft_wiki.backend.Repo.payment;

import com.minecraft_wiki.backend.Model.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository
        extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByYookassaPaymentId(String id);
}
