package com.project.exchangerates.provider;

import org.springframework.http.HttpEntity;

public interface HeadersProvider {

    HttpEntity getHeadersHttpEntity();
}
