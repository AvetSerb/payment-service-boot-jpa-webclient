package com.example.payments.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentResult (
        Long paymentId,
        BigDecimal amountRub,
        BigDecimal fee
){}
