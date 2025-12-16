package com.minecraft_wiki.backend.Model.payment;

public class CreatePaymentRequest {

    private String value;
    private String orderId;
    private String userId;

    public String getValue() {
        return value;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }
}
