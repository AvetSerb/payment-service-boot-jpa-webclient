package com.example.payments.service;

import com.example.payments.domain.Payment;

public interface PaymentStore {
    Payment save(Payment payment);
    Long transactionalSave(Payment payment, Long id);

}
