package com.project.exchangerates.service;

import com.project.exchangerates.provider.ExchangeRateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CurrencyConversionServiceTest {

    @Mock
    private ExchangeRateProvider exchangeRateProvider;

    @InjectMocks
    private CurrencyConversionService currencyConversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenRateKnown_shouldSuccess() {
        when(exchangeRateProvider.getRate("USD", "EUR")).thenReturn(1.2);
        String result = currencyConversionService.convertCurrency(100.0, "USD", "EUR");
        assertEquals("120.00", result);
    }

    @Test
    void whenRateProviderThrowsException_shouldThrowException() {
        when(exchangeRateProvider.getRate("USD", "KNR")).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> currencyConversionService.convertCurrency(100, "USD", "KNR"));
    }

}
