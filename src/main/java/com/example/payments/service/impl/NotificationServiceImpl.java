package com.example.payments.service.impl;

import com.example.payments.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
//    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void notifyPaymentCreated(Long paymentId) {
        // Demo: just log. Replace with external REST call if needed.
        log.info("Notification sent for payment {}", paymentId);
    }
}
