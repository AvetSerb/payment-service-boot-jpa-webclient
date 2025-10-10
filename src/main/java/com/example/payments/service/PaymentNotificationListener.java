package com.example.payments.service;

import com.example.payments.ports.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentNotificationListener {
    private static final Logger log = LoggerFactory.getLogger(PaymentNotificationListener.class);
    private final NotificationService notificationService;

    public PaymentNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPaymentCreated(PaymentCreatedEvent event) {
        try {
            notificationService.notifyPaymentCreated(event.paymentId());
        } catch (Exception e) {
            log.warn("Failed to notify for payment {}: {}", event.paymentId(), e.getMessage());
        }
    }
}
