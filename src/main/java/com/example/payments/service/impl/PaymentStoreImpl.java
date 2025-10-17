package com.example.payments.service.impl;

import com.example.payments.model.Payment;
import com.example.payments.repo.PaymentRepository;
import com.example.payments.service.PaymentStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentStoreImpl implements PaymentStoreService {
    private final PaymentRepository repo;

    @Override
    public Payment save(Payment payment) {
        return repo.save(payment);
    }


    public Payment findById(Long id) {
        return repo.findById(id).orElse(null);
    }


    public Long transactionalSave(Payment payment, Long id) {
        var ids = findById(id);

        repo.save(payment);
        return id;
    }
}