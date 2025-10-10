package com.example.payments.web;

import com.example.payments.service.PaymentService;
import com.example.payments.web.dto.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> pay(@RequestHeader("X-Payer-Id") Long payerId,
                                 @Validated @RequestBody PaymentRequest dto) {
        Long paymentId = paymentService.processPayment(dto.amount(), dto.currencyCode(), payerId, dto.recipientId());
        return ResponseEntity.ok(Map.of("paymentId", paymentId));
    }
}
