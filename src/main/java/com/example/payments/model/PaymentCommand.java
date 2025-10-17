package com.example.payments.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentCommand(
        Long payerId,
        Long recipientId,
        BigDecimal amount,
        String currencyCode) {
}
