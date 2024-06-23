package com.project.exchangerates.provider.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwopHeadersProviderTest {

    @InjectMocks
    private SwopHeadersProvider swopHeadersProvider;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        injectApiKey(swopHeadersProvider, "test-api-key");
    }

    @Test
    public void whenApiKeyDefined_shouldCreateHeadersEntity() {
        HttpEntity httpEntity = swopHeadersProvider.getHeadersHttpEntity();
        HttpHeaders headers = httpEntity.getHeaders();
        assertEquals("ApiKey test-api-key", headers.getFirst("Authorization"));
    }

    private void injectApiKey(SwopHeadersProvider target, String value) throws Exception {
        Field field = target.getClass().getDeclaredField("apiKey");
        field.setAccessible(true);
        field.set(target, value);
    }

}
