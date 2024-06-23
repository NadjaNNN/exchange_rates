package com.project.exchangerates.controller;

import com.project.exchangerates.service.CurrencyConversionService;
import com.project.exchangerates.service.SupportedCurrenciesService;
import com.project.exchangerates.service.exceptions.InvalidCurrencyCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CurrencyConversionControllerTest {

    @InjectMocks
    private CurrencyConversionController currencyConversionController;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @Mock
    private SupportedCurrenciesService supportedCurrenciesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenValidInput_shouldConvert() {
        when(currencyConversionService.convertCurrency(100, "EUR", "USD")).thenReturn("100");
        when(supportedCurrenciesService.getCurrencies()).thenReturn(Arrays.asList("EUR", "USD"));
        assertEquals("100", currencyConversionController.getConversions(100, "EUR", "USD"));
    }

    @Test
    public void whenInvalidFromCurrency_ThrowsException() {
        assertThrows(InvalidCurrencyCodeException.class, () -> currencyConversionController.getConversions(10.0, "INVALID", "EUR"));
    }

    @Test
    public void whenInvalidToCurrency_ThrowsException() {
        assertThrows(InvalidCurrencyCodeException.class, () -> currencyConversionController.getConversions(10.0, "EUR", "INVALID"));
    }

    @Test
    public void whenInvalidCurrencyCodeException_shouldHandleErrorMessage() {
        InvalidCurrencyCodeException exception = new InvalidCurrencyCodeException("Invalid currency code");
        String response = currencyConversionController.handleInvalidCurrencyCodeException(exception);
        assertEquals("Invalid currency code", response);
    }

    @Test
    public void whenServiceThrowsException_shouldThrowException() {
        when(currencyConversionService.convertCurrency(100, "EUR", "USD")).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> currencyConversionController.getConversions(100, "EUR", "USD"));
    }
}
