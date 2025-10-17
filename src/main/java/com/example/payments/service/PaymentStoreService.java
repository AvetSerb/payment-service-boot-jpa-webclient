package com.example.payments.service;

import com.example.payments.model.Payment;

public interface PaymentStoreService {
    Payment save(Payment payment);
    Long transactionalSave(Payment payment, Long id);

}
