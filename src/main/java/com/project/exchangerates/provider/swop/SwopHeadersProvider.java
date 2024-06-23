package com.project.exchangerates.provider.swop;

import com.project.exchangerates.provider.HeadersProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class SwopHeadersProvider implements HeadersProvider {

    @Value("${exchange-rate-provider.swop.api-key}")
    private String apiKey;

    public HttpEntity getHeadersHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "ApiKey " + apiKey);
        return new HttpEntity<>(headers);
    }
}
