package com.LDE.monFax_backend.repositories;

import com.LDE.monFax_backend.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Long> {
}
