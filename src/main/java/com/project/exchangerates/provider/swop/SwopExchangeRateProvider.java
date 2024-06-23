package com.project.exchangerates.provider.swop;

import com.project.exchangerates.provider.ExchangeRateProvider;
import com.project.exchangerates.provider.HeadersProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SwopExchangeRateProvider implements ExchangeRateProvider {

    private Logger logger = LoggerFactory.getLogger(SwopExchangeRateProvider.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HeadersProvider headersProvider;

    @Value("${exchange-rate-provider.swop.base-url}")
    private String url;

    @Override
    public Double getRate(String sourceCurrency, String targetCurrency) {
        logger.info("Fetching exchange rate from {} to {}", sourceCurrency, targetCurrency);
        String urlPath = url + "/rates/" + sourceCurrency + "/" + targetCurrency;
        ResponseEntity<SwopExchangeRateResponse> response = restTemplate.exchange(
                urlPath, HttpMethod.GET, headersProvider.getHeadersHttpEntity(), SwopExchangeRateResponse.class);
        Double exchangeRate = response.getBody().getQuote();
        logger.info("Fetched exchange change rate of {} from {} to {}", exchangeRate, sourceCurrency, targetCurrency);
        return exchangeRate;
    }

}
