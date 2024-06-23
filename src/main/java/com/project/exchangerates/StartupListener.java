package com.project.exchangerates;

import com.project.exchangerates.provider.CurrenciesProvider;
import com.project.exchangerates.service.SupportedCurrenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupListener {

    @Autowired
    private SupportedCurrenciesService supportedCurrenciesService;

    @Autowired
    private CurrenciesProvider currenciesProvider;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        List<String> currencies = currenciesProvider.getCurrencies();
        supportedCurrenciesService.setCurrencies(currencies);
    }

}
