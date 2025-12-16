package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.payment.CreatePaymentRequest;
import com.minecraft_wiki.backend.Model.payment.PaymentResponse;
import com.minecraft_wiki.backend.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse create(@RequestBody CreatePaymentRequest request) {
        return paymentService.createPayment(request);
    }
}
