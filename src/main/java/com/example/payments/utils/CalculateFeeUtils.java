package com.example.payments.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class CalculateFeeUtils {

    public BigDecimal calculateFee (BigDecimal amountRub){
        if (amountRub.compareTo(new BigDecimal("1000")) < 0) {
            return amountRub.multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
        } else if (amountRub.compareTo(new BigDecimal("5000")) <= 0) {
            return amountRub.multiply(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP);
        } else {
            return amountRub.multiply(new BigDecimal("0.005")).setScale(2, RoundingMode.HALF_UP);
        }
    }
}
