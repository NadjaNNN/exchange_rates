package com.project.exchangerates.provider.swop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SwopExchangeRateResponse {

    private Double quote;

}
