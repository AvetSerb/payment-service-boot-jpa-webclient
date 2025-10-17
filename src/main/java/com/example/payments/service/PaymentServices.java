package com.example.payments.service;

import com.example.payments.model.PaymentCommand;
import com.example.payments.model.PaymentResult;

public interface PaymentServices {
    PaymentResult process(PaymentCommand command);
}
