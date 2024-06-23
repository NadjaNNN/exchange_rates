package com.project.exchangerates.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class SupportedCurrenciesService {

    private List<String> currencies;

}
