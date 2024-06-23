package com.project.exchangerates.provider.swop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SwopCurrencyResponse {

    private String code;

    private Boolean active;
}
