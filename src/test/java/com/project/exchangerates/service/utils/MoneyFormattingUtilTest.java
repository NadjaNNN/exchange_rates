package com.project.exchangerates.service.utils;

import com.project.exchangerates.service.utils.MoneyFormattingUtil;
import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyFormattingUtilTest {

    @Test
    public void allCurrencies_shouldBeFormatted() {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        for (Currency currency : currencies) {
            assertEquals("112,356.58", MoneyFormattingUtil.format(112356.58, currency.getCurrencyCode()));
        }
    }

}
