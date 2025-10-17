package com.example.payments.mapper;

import com.example.payments.domain.Payment;
import com.example.payments.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {


    public PaymentDto convertToDto(Payment entity) {
        return PaymentDto.builder()
                .id(entity.getId())
                .amountRub(entity.getAmountRub())
                .payer(entity.getPayer())
                .recipient(entity.getRecipient())
                .bookingDate(entity.getBookingDate())
                .build();
    }


    public Payment convertToEntity(PaymentDto dto) {
        return Payment.builder()
                .id(dto.id())
                .amountRub(dto.amountRub())
                .payer(dto.payer())
                .recipient(dto.recipient())
                .bookingDate(dto.bookingDate())
                .build();
    }
}
