package org.javaacademy.rub_bitcoin.service.integration;

import lombok.RequiredArgsConstructor;
import org.javaacademy.rub_bitcoin.config.FreeCurrencyConfigurationProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FreeCurrencyIntegrationService {
    private static final String CURRENCY_RUB = "RUB";
    private static final String CURRENCY_PARAM_NAME = "currencies";
    private final FreeCurrencyConfigurationProperties properties;
    private final IntegrationService integrationService;

    public BigDecimal getCurrentUsdPriceInRub() {
        Map<String, String> requestParams = Map.of(CURRENCY_PARAM_NAME, CURRENCY_RUB);
        return integrationService.getFieldValue(properties.getUrl(),
                "/data/RUB",
                BigDecimal.class,
                properties.getTokenHeaderName(),
                properties.getApiKey(),
                requestParams);
    }

}
