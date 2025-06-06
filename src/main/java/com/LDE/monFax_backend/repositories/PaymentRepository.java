package com.LDE.monFax_backend.repositories;

import com.LDE.monFax_backend.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Long> {
    List<Payment> findTop5ByOrderByIdDesc();
}
