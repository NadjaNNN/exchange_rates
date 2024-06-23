package com.project.exchangerates.service.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyFormattingUtil {

    public static String format(double amount, String currencyCode) {
        Currency currency = Currency.getInstance(currencyCode);
        Locale locale = new Locale("", currency.getNumericCodeAsString());
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(amount).substring(2);
    }

}
