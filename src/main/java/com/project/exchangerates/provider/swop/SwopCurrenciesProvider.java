package com.project.exchangerates.provider.swop;

import com.project.exchangerates.provider.CurrenciesProvider;
import com.project.exchangerates.provider.HeadersProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwopCurrenciesProvider implements CurrenciesProvider {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HeadersProvider headersProvider;

    @Value("${exchange-rate-provider.swop.base-url}")
    private String url;

    public List<String> getCurrencies() {
        String urlPath = url + "/currencies";
        ResponseEntity<List<SwopCurrencyResponse>> response = restTemplate.exchange(
                urlPath,
                HttpMethod.GET,
                headersProvider.getHeadersHttpEntity(),
                new ParameterizedTypeReference<List<SwopCurrencyResponse>>() {});
        return response.getBody().stream()
                .filter(c -> c.getActive().booleanValue())
                .map(SwopCurrencyResponse::getCode)
                .collect(Collectors.toList());
    }

}
