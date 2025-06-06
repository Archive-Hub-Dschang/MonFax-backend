package controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LDE.monFax_backend.models.Payment;
import com.LDE.monFax_backend.repositories.PaymentRepository;
import com.LDE.monFax_backend.models.User;
import com.LDE.monFax_backend.repositories.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/last-payments")
    public List<Payment> getLastPayments() {
        return paymentRepository.findTop5ByOrderByIdDesc();
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/last-logins")
    public List<User> getLastLogins() {
    return userRepository.findTop5ByOrderByLastLoginDesc();
}
}
