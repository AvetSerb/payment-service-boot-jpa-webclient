package com.example.payments.service;

import com.example.payments.domain.Fee;
import com.example.payments.domain.Payment;
import com.example.payments.domain.User;
import com.example.payments.dto.PaymentDto;
import com.example.payments.mapper.PaymentMapper;
import com.example.payments.ports.ExchangeRateService;
import com.example.payments.repo.FeeRepository;
import com.example.payments.repo.PaymentRepository;
import com.example.payments.utils.CalculateFeeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService { //Добавить интерфейс, а эту логику вынести в имплементацию
    private final ExchangeRateService exchangeRateService;
    private final UserService userService;
    private final PaymentRepository paymentRepository; //Переписать под сервис
    private final FeeRepository feeRepository; //Переписать под сервис
    private final ApplicationEventPublisher events;
    private final PaymentMapper paymentMapper;
    private final CalculateFeeUtils calculateFeeUtils;

    @Transactional(readOnly = true)
    public Long processPayment(BigDecimal amount, String currencyCode, Long payerId, Long recipientId) {
//        User payer = userRepository.findById(payerId)
//                .orElseThrow(() -> new IllegalArgumentException("Payer not found: " + payerId));
        Payment paymentEntity = new Payment();

        try {
//            var payer = userRepository.findById(payerId)
//                    .orElseThrow(() -> new IllegalArgumentException("Payer not found: " + payerId));
//
//            var recipient = userRepository.findById(recipientId)
//                    .orElseThrow(() -> new IllegalArgumentException("Recipient not found: " + recipientId));
            var payer = findUserById(payerId);

            var recipient = findUserById(recipientId);

            if (payer.getId().equals(recipient.getId())) {
                throw new IllegalArgumentException("Cannot pay to self");
            }

            var rate = exchangeRateService.rateForToday(currencyCode, "RUB"); // в отдельный метод
            var amountRub = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP); // в отдельный метод


//       var payment = new PaymentDto(amountRub,payer,recipient, LocalDate.now());

            var payment = PaymentDto.builder()
                    .amountRub(amountRub.subtract(calculateFeeUtils.calculateFee(amountRub)))
                    .payer(payer)
                    .recipient(recipient)
                    .bookingDate(LocalDate.now())
                    .build();

            paymentEntity = paymentMapper.convertToEntity(payment);

            paymentRepository.save(paymentEntity);

//        Payment payment = new Payment();
//        payment.setAmountRub(amountRub.subtract(calculateFee(amountRub)));
//        payment.setPayer(payer);
//        payment.setRecipient(recipient);
//        payment.setBookingDate(LocalDate.now());
//        paymentRepository.save(payment);

//            var fee = Fee.builder()
//                    .value(calculateFee(amountRub))
//                    .user(payer)
//                    .payment(paymentEntity)
//                    .build();

//        var fee = new Fee();
//        fee.setValue(calculateFee(amountRub));
//        fee.setUser(payer);
//        fee.setPayment(payment);
//            feeRepository.save(fee);
//
            calculateFee(amountRub, payer, paymentEntity);

            events.publishEvent(new PaymentCreatedEvent(paymentEntity.getId()));
//            log.info("events write paymentId:{}", events.toString()); // поразмыслить как реально вывести в логи id, будто уведомляем
            return paymentEntity.getId();

        }catch (Exception e) {
            log.error("[processPayment] Что-то произошло: {}", e.getMessage());
        }
        return paymentEntity.getId();
    }


    private void calculateFee(BigDecimal amount, User user, Payment paymentEntity) {
            var fee = Fee.builder()
                    .value(calculateFeeUtils.calculateFee(amount))
                    .user(user)
                    .payment(paymentEntity)
                    .build();

            feeRepository.save(fee);
        }

        private User findUserById(Long userId) {
            return userService.findById(userId).orElseThrow();
        }
    }
