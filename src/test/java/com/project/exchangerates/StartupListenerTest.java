package com.project.exchangerates;

import com.project.exchangerates.provider.CurrenciesProvider;
import com.project.exchangerates.service.SupportedCurrenciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartupListenerTest {

    @Mock
    private SupportedCurrenciesService supportedCurrenciesService;

    @Mock
    private CurrenciesProvider currenciesProvider;

    @InjectMocks
    private StartupListener startupListener;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void onApplicationReady_shouldGetCurrencies() {
        List<String> mockCurrencies = Arrays.asList("USD", "EUR", "JPY");
        when(currenciesProvider.getCurrencies()).thenReturn(mockCurrencies);

        startupListener.onApplicationReady();

        verify(currenciesProvider, times(1)).getCurrencies();
        verify(supportedCurrenciesService, times(1)).setCurrencies(mockCurrencies);
    }
}

