package com.example.payments.service.impl;

import com.example.payments.dto.PaymentDto;
import com.example.payments.listeners.events.PaymentCreatedEvent;
import com.example.payments.mapper.PaymentMapper;
import com.example.payments.model.PaymentCommand;
import com.example.payments.model.PaymentResult;
import com.example.payments.service.*;
import com.example.payments.utils.CalculateFeeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final UserService userService;
    private final ExchangeRateService exchangeRateService;
    private final FeePolicyService feePolicyService;
    private final PaymentStoreService paymentStoreService;
    private final PaymentMapper paymentMapper;
    private final ApplicationEventPublisher events;
    private final CalculateFeeUtils calculateFeeUtils;


    @Override
    @Transactional
    public PaymentResult process(PaymentCommand command) {
        try {
            var payer = userService.findById(command.payerId()).orElseThrow();

            var recipient = userService.findById(command.recipientId()).orElseThrow();

            if (payer.equals(recipient)) {
                throw new IllegalArgumentException("Cannot pay to self");
            }

            var rate = exchangeRateService.rateForToday(command.currencyCode(), "RUB"); // в отдельный метод
            var amountRub = command.amount().multiply(rate).setScale(2, RoundingMode.HALF_UP); // в отдельный метод

            var payment = PaymentDto.builder()
                    .amountRub(amountRub.subtract(calculateFeeUtils.calculateFee(amountRub)))
                    .payer(payer)
                    .recipient(recipient)
                    .bookingDate(LocalDate.now())
                    .build();

            var paymentEntity = paymentMapper.toEntity(payment);

            paymentStoreService.save(paymentEntity);
            var fee = feePolicyService.calculate(amountRub, payer, paymentEntity);

            events.publishEvent(new PaymentCreatedEvent(paymentEntity.getId()));
            return PaymentResult.builder()
                    .paymentId(paymentEntity.getId())
                    .amountRub(amountRub)
                    .fee(fee)
                    .build();

        } catch (Exception e) {
            log.error("[processPayment] Что-то произошло: {}", e.getMessage());
            return null;
        }
    }
}
