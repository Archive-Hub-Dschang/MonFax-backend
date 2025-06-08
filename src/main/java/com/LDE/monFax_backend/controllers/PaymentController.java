package com.LDE.monFax_backend.controllers;

import com.LDE.monFax_backend.models.Payment;
import com.LDE.monFax_backend.services.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments/last5")
    public List<Payment> getLast5Payments() {
        return paymentService.getLast5Payments();
    }
}
