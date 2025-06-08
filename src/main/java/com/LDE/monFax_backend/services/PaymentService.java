package com.LDE.monFax_backend.services;

import com.LDE.monFax_backend.models.Payment;
import com.LDE.monFax_backend.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getLast5Payments() {
        return paymentRepository.findTop5ByOrderByIdDesc();
    }
}
