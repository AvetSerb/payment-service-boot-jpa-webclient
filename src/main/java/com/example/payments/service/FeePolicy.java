package com.example.payments.service;

import java.math.BigDecimal;

public interface FeePolicy {
    BigDecimal calculate(BigDecimal amountRub);
}
