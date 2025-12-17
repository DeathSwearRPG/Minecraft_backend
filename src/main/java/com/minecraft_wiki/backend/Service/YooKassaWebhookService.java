package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.payment.entity.PaymentEntity;
import com.minecraft_wiki.backend.Model.payment.entity.PlayerPrivilegeEntity;
import com.minecraft_wiki.backend.Repo.payment.PaymentRepository;
import com.minecraft_wiki.backend.Repo.payment.PlayerPrivilegeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

@Service
public class YooKassaWebhookService {

    private final PaymentRepository paymentRepository;
    private final PlayerPrivilegeRepository privilegeRepository;

    public YooKassaWebhookService(
            PaymentRepository paymentRepository,
            PlayerPrivilegeRepository privilegeRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Transactional
    public void handle(Map<String, Object> payload) {

        Map<String, Object> object =
                (Map<String, Object>) payload.get("object");

        String status = (String) object.get("status");
        if (!"succeeded".equals(status)) {
            return; // игнорируем неуспешные события
        }

        Map<String, Object> metadata =
                (Map<String, Object>) object.get("metadata");

        UUID paymentId = UUID.fromString(
                (String) metadata.get("paymentId")
        );

        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElse(null);

        if (payment == null) {
            return;
        }

        // Идемпотентность
        if ("SUCCEEDED".equals(payment.getStatus())) {
            return;
        }

        Map<String, Object> amount =
                (Map<String, Object>) object.get("amount");

        BigDecimal paidAmount =
                new BigDecimal((String) amount.get("value"));

        if (paidAmount.compareTo(payment.getAmountValue()) != 0) {
            throw new IllegalStateException("Amount mismatch");
        }

        payment.setStatus("SUCCEEDED");
        payment.setPaidAt(Instant.now());

        PlayerPrivilegeEntity privilege = new PlayerPrivilegeEntity();
        privilege.setId(UUID.randomUUID());
        privilege.setPlayerName(payment.getPlayerName());
        privilege.setPrivilegeCode(payment.getProductCode());
        privilege.setStartsAt(Instant.now());
        privilege.setEndsAt(
                Instant.now().plus(30, ChronoUnit.DAYS)
        );
        privilege.setStatus("ACTIVE");
        privilege.setSourcePaymentId(payment.getId());

        privilegeRepository.save(privilege);
        paymentRepository.save(payment);
    }
}
