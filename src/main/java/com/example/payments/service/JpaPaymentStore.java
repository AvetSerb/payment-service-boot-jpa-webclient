package com.example.payments.service;

import com.example.payments.domain.Payment;
import com.example.payments.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaPaymentStore implements PaymentStore {
    private final PaymentRepository repo;

    @Override
    public Payment save(Payment payment) {
        return repo.save(payment);
    }
}