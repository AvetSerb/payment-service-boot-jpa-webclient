package com.example.payments.listeners.events;

import lombok.Builder;

@Builder
public record PaymentCreatedEvent(Long paymentId) {}
