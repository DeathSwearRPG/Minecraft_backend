package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Client.YooKassaClient;
import com.minecraft_wiki.backend.Model.payment.CreatePaymentRequest;
import com.minecraft_wiki.backend.Model.payment.PaymentResponse;
import com.minecraft_wiki.backend.Model.payment.entity.PaymentEntity;
import com.minecraft_wiki.backend.Model.payment.entity.ProductEntity;
import com.minecraft_wiki.backend.Repo.payment.PaymentRepository;
import com.minecraft_wiki.backend.Repo.payment.ProductRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {

    private final YooKassaClient yooKassaClient;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(
            YooKassaClient yooKassaClient,
            ProductRepository productRepository,
            PaymentRepository paymentRepository
    ) {
        this.yooKassaClient = yooKassaClient;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {

        ProductEntity product = productRepository.findById(request.getProductCode())
                .orElseThrow(() -> new IllegalArgumentException("Unknown product"));

        UUID internalPaymentId = UUID.randomUUID();

        PaymentEntity payment = new PaymentEntity();
        payment.setId(internalPaymentId);
        payment.setProductCode(product.getCode());
        payment.setPlayerName(request.getPlayerName());
        payment.setStatus("NEW");
        payment.setAmountValue(product.getPriceValue());
        payment.setCurrency(product.getCurrency());

        paymentRepository.save(payment);

        // вызываем YooKassa
        PaymentResponse ykResponse = yooKassaClient.createPayment(
                internalPaymentId,
                product.getCode(),
                request.getPlayerName(),
                product.getPriceValue(),
                product.getCurrency()
        );

        // сохраняем ДАННЫЕ ОТ YooKassa В БД
        payment.setStatus(ykResponse.getStatus());
        payment.setYookassaPaymentId(ykResponse.getPaymentId()); // ТОЛЬКО В БД
        payment.setConfirmationUrl(ykResponse.getConfirmationUrl());

        paymentRepository.save(payment);

        return new PaymentResponse(
                internalPaymentId.toString(), // ← КЛЮЧЕВО
                payment.getStatus(),
                payment.getConfirmationUrl()
        );
    }

}
