package com.example.payments.service;

import java.math.BigDecimal;

public interface PaymentServices {
    PaymentResult process(PaymentCommand command);
    record PaymentCommand(Long payerId, Long recipientId, BigDecimal amount, String currencyCode) {}
    record PaymentResult(Long paymentId, BigDecimal amountRub, BigDecimal fee) {}
}
