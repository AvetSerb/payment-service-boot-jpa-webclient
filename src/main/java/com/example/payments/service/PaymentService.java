package com.example.payments.service;

import com.example.payments.model.PaymentCommand;
import com.example.payments.model.PaymentResult;

public interface PaymentService {
    PaymentResult process(PaymentCommand command);
}
