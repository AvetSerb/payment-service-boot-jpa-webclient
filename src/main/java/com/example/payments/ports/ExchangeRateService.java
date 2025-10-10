package com.example.payments.ports;

import java.math.BigDecimal;

public interface ExchangeRateService {
    BigDecimal rateForToday(String currencyCode, String targetCurrencyCode);
}
