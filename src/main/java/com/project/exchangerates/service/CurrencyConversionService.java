package com.project.exchangerates.service;

import com.project.exchangerates.service.utils.MoneyFormattingUtil;
import com.project.exchangerates.provider.ExchangeRateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

    @Autowired
    private ExchangeRateProvider exchangeRateProvider;

    @Cacheable("rates")
    public String convertCurrency(double amount, String from, String to) {
        Double exchangeRate = getExchangeRate(from, to);
        return MoneyFormattingUtil.format(exchangeRate * amount, to);
    }

    private Double getExchangeRate(String from, String to) {
        return exchangeRateProvider.getRate(from, to);
    }

    @CacheEvict(value = "rates", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.rates-cache-ttl}")
    public void evictRatesCache() {
        logger.info("Evicting rates cache");
    }
}
