package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Client.YooKassaClient;
import com.minecraft_wiki.backend.Model.payment.CreatePaymentRequest;
import com.minecraft_wiki.backend.Model.payment.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final YooKassaClient yooKassaClient;

    public PaymentService(YooKassaClient yooKassaClient) {
        this.yooKassaClient = yooKassaClient;
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {
        return yooKassaClient.createPayment(request);
    }
}
