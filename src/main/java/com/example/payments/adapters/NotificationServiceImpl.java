package com.example.payments.adapters;

import com.example.payments.ports.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void notifyPaymentCreated(Long paymentId) {
        // Demo: just log. Replace with external REST call if needed.
        log.info("Notification sent for payment {}", paymentId);
    }
}
