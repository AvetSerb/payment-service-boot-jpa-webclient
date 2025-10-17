package com.example.payments.service.impl;

import com.example.payments.service.ExchangeRateService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Demo implementation. In production call CBR API here and parse rate for today.
 * To keep the sample self-contained and offline-friendly, we return stubbed rates.
 */
@Service
public class CbrExchangeRateServiceImpl implements ExchangeRateService {

    private final WebClient webClient;

    public CbrExchangeRateServiceImpl(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    private static final Map<String, BigDecimal> STUB = Map.of(
            "USD", new BigDecimal("100.00"),
            "EUR", new BigDecimal("110.00"),
            "RUB", BigDecimal.ONE
    );

    @Override
//    @Cacheable(cacheNames = "cbrRates", key = "#currencyCode")
    public BigDecimal rateForToday(String currencyCode, String targetCurrencyCode) {
        if (!"RUB".equalsIgnoreCase(targetCurrencyCode)) {
            throw new IllegalArgumentException("Only RUB target is supported in demo");
        }
        return STUB.getOrDefault(currencyCode.toUpperCase(), BigDecimal.ONE);
    }
}
