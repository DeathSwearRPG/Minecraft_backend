package com.minecraft_wiki.backend.Model.payment;

public class PaymentResponse {

    private final String paymentId;
    private final String status;
    private final String confirmationUrl;

    public PaymentResponse(String paymentId, String status, String confirmationUrl) {
        this.paymentId = paymentId;
        this.status = status;
        this.confirmationUrl = confirmationUrl;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }

    public String getConfirmationUrl() {
        return confirmationUrl;
    }
}
