package com.example.payments.service;

import com.example.payments.domain.Payment;
import com.example.payments.domain.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
@Component
public class PaymentMapper {
    public Payment toEntity(BigDecimal amountRub, User payer, User recipient) {
        var p = new Payment();
        p.setAmountRub(amountRub);
        p.setPayer(payer);
        p.setRecipient(recipient);
        p.setBookingDate(LocalDate.now());
        return p;
    }
}
