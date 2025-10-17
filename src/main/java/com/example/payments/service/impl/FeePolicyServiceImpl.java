package com.example.payments.service.impl;

import com.example.payments.model.Fee;
import com.example.payments.model.Payment;
import com.example.payments.model.User;
import com.example.payments.repo.FeeRepository;
import com.example.payments.service.FeePolicyService;
import com.example.payments.utils.CalculateFeeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FeePolicyServiceImpl implements FeePolicyService {

    private final CalculateFeeUtils calculateFeeUtils;
    private final FeeRepository feeRepository;

    @Override
    public Fee calculate(BigDecimal amountRub, User user, Payment paymentEntity) {
        return feeRepository.save(Fee.builder()
                .value(calculateFeeUtils.calculateFee(amountRub))
                .user(user)
                .payment(paymentEntity)
                .build());
    }
}
