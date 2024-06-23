package com.project.exchangerates.provider;

public interface ExchangeRateProvider {
    Double getRate(String sourceCurrency, String targetCurrency);
}
