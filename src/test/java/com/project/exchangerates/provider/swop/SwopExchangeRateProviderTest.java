package com.project.exchangerates.provider.swop;

import com.project.exchangerates.provider.HeadersProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class SwopExchangeRateProviderTest {

    @InjectMocks
    private SwopExchangeRateProvider swopExchangeRateProvider;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private HeadersProvider headersProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(headersProvider.getHeadersHttpEntity()).thenReturn(new HttpEntity<>(new HttpHeaders()));
    }

    @Test
    public void whenApiReturnsData_shouldReturnData() {
        SwopExchangeRateResponse rateResponse = new SwopExchangeRateResponse();
        rateResponse.setQuote(1.2);
        when(restTemplate.exchange(
                anyString(), // URL
                eq(HttpMethod.GET), // HttpMethod
                any(HttpEntity.class), // HttpEntity
                eq(SwopExchangeRateResponse.class))) // ResponseType
                .thenReturn(ResponseEntity.ok(rateResponse));
        assertEquals(rateResponse.getQuote(), swopExchangeRateProvider.getRate("EUR", "USD"));
    }

    @Test
    public void whenSwopThrowsException_shouldThrowException() {
        when(restTemplate.exchange(
                anyString(), // URL
                eq(HttpMethod.GET), // HttpMethod
                any(HttpEntity.class), // HttpEntity
                eq(SwopExchangeRateResponse.class))) // ResponseType
                .thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> swopExchangeRateProvider.getRate("EUR", "USD"));
    }

}
