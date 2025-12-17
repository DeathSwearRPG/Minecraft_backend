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

        UUID paymentId = UUID.randomUUID();

        PaymentEntity payment = new PaymentEntity();
        payment.setId(paymentId);
        payment.setProductCode(product.getCode());
        payment.setPlayerName(request.getPlayerName());
        payment.setStatus("NEW");
        payment.setAmountValue(product.getPriceValue());
        payment.setCurrency(product.getCurrency());

        paymentRepository.save(payment);

        PaymentResponse response = yooKassaClient.createPayment(
                paymentId,
                product.getCode(),
                request.getPlayerName(),
                product.getPriceValue(),
                product.getCurrency()
        );


        payment.setStatus(response.getStatus());
        payment.setYookassaPaymentId(response.getPaymentId());
        payment.setConfirmationUrl(response.getConfirmationUrl());

        paymentRepository.save(payment);

        return response;
    }
}
