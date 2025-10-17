package com.example.payments.service.impl;

import com.example.payments.model.PaymentCommand;
import com.example.payments.model.PaymentResult;
import com.example.payments.ports.ExchangeRateService;
import com.example.payments.repo.FeeRepository;
import com.example.payments.repo.PaymentRepository;
import com.example.payments.service.PaymentService;
import com.example.payments.service.PaymentServices;
import com.example.payments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentServices {
    private final ExchangeRateService exchangeRateService;
    private final UserService userService;
    private final PaymentRepository paymentRepository;
    private final FeeRepository feeRepository;
    private final ApplicationEventPublisher events;


    @Override
    public PaymentResult process(PaymentCommand command) {
        return null;
    }
}
