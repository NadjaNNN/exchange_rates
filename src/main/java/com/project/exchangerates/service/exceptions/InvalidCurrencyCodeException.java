package com.project.exchangerates.service.exceptions;

public class InvalidCurrencyCodeException extends RuntimeException {

    public InvalidCurrencyCodeException(String message) {
        super(message);
    }
}
