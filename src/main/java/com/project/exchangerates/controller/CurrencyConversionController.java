package com.project.exchangerates.controller;

import com.project.exchangerates.service.SupportedCurrenciesService;
import com.project.exchangerates.service.exceptions.InvalidCurrencyCodeException;
import com.project.exchangerates.service.CurrencyConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Autowired
    private SupportedCurrenciesService supportedCurrenciesService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/conversions")
    public String getConversions(
            @RequestParam double amount,
            @RequestParam String from,
            @RequestParam String to) {
        logger.info("Received conversion request: amount {}, from {}, to {}", amount, from, to);
        validateCurrencies(from, to);
        String response = currencyConversionService.convertCurrency(amount, from, to);
        logger.info("Returning conversion response: converted amount {}, original amount {}, from {}, to {}", response, amount, from, to);

        return response;
    }

    private void validateCurrencies(String from, String to) {
        List<String> currencies = supportedCurrenciesService.getCurrencies();
        if (!currencies.contains(from)) {
            throw new InvalidCurrencyCodeException("Invalid currency code from: " + from);
        }
        if (!currencies.contains(to)) {
            throw new InvalidCurrencyCodeException("Invalid currency code to: " + to);
        }
    }

    @ExceptionHandler(InvalidCurrencyCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCurrencyCodeException(InvalidCurrencyCodeException e) {
        return e.getMessage();
    }
}
