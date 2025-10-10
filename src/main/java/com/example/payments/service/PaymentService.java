package com.example.payments.service;

import com.example.payments.domain.Fee;
import com.example.payments.domain.Payment;
import com.example.payments.domain.User;
import com.example.payments.ports.ExchangeRateService;
import com.example.payments.repo.FeeRepository;
import com.example.payments.repo.PaymentRepository;
import com.example.payments.repo.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
@Service
@NoArgsConstructor(force = true)
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final FeeRepository feeRepository;
    private final UserRepository userRepository;
    private final ExchangeRateService exchangeRateService;
    private final ApplicationEventPublisher events;

    @Transactional(readOnly = true)
    public Long processPayment(BigDecimal amount, String currencyCode, Long payerId, Long recipientId) {
        User payer = userRepository.findById(payerId)
                .orElseThrow(() -> new IllegalArgumentException("Payer not found: " + payerId));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found: " + recipientId));
        if (payer.getId().equals(recipient.getId())) {
            throw new IllegalArgumentException("Cannot pay to self");
        }

        var rate = exchangeRateService.rateForToday(currencyCode, "RUB");
        var amountRub = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        Payment payment = new Payment();
        payment.setAmountRub(amountRub);
        payment.setPayer(payer);
        payment.setRecipient(recipient);
        payment.setBookingDate(LocalDate.now());
        paymentRepository.save(payment);

        var fee = new Fee();
        fee.setValue(calculateFee(amountRub));
        fee.setUser(payer);
        fee.setPayment(payment);
        feeRepository.save(fee);

        events.publishEvent(new PaymentCreatedEvent(payment.getId()));
        return payment.getId();
    }

    private static BigDecimal calculateFee(BigDecimal amountRub) {
        if (amountRub.compareTo(new BigDecimal("1000")) < 0) {
            return amountRub.multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
        } else if (amountRub.compareTo(new BigDecimal("5000")) <= 0) {
            return amountRub.multiply(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_UP);
        } else {
            return amountRub.multiply(new BigDecimal("0.005")).setScale(2, RoundingMode.HALF_UP);
        }
    }
}
