package com.example.payments.listeners;

import com.example.payments.listeners.events.PaymentCreatedEvent;
import com.example.payments.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentNotificationListener {

    private final NotificationService notificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPaymentCreated(PaymentCreatedEvent event) {
        try {
            notificationService.notifyPaymentCreated(event.paymentId());
        } catch (Exception e) {
            log.warn("Failed to notify for payment {}: {}", event.paymentId(), e.getMessage());
        }
    }
}
