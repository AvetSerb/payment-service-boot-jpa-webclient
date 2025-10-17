package com.example.payments.service;

import com.example.payments.model.Fee;
import com.example.payments.model.Payment;
import com.example.payments.model.User;

import java.math.BigDecimal;

public interface FeePolicyService {
    Fee calculate(BigDecimal amountRub, User user, Payment paymentEntity);
}
