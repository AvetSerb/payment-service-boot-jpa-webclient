package com.example.payments.dto;

import com.example.payments.domain.User;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
public record PaymentDto(
        Long id,
        BigDecimal amountRub,
        User payer,
        User recipient,
        LocalDate bookingDate
) {
}

