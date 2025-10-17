package com.example.payments.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ThresholdFeePolicy implements FeePolicy{
    @Override
    public BigDecimal calculate(BigDecimal amountRub) {
        if (amountRub.compareTo(new BigDecimal("1000")) < 0) {
            return amountRub.multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
        } else if (amountRub.compareTo(new BigDecimal("5000")) <= 0) {
            return amountRub.multiply(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP);
        } else {
            return amountRub.multiply(new BigDecimal("0.005")).setScale(2, RoundingMode.HALF_UP);
        }
    }

}
