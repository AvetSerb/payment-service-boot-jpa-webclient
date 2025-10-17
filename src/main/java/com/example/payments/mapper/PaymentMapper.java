package com.example.payments.mapper;

import com.example.payments.model.Payment;
import com.example.payments.dto.PaymentDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentMapper {
    public Payment toEntity(PaymentDto payment) {
        var p = new Payment();
        p.setAmountRub(payment.amountRub());
        p.setPayer(payment.payer());
        p.setRecipient(payment.recipient());
        p.setBookingDate(LocalDate.now());
        return p;
    }
}
