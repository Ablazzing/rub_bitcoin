package org.javaacademy.rub_bitcoin.service.integration;

import lombok.RequiredArgsConstructor;
import org.javaacademy.rub_bitcoin.exception.IntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CoinDeskIntegrationService {
    private final IntegrationService integrationService;

    @Value("${bitcoin.integration.coin_desk.url}")
    private String coinDeskServiceUrl;

    /**
     * @return - Возвращает стоимость биткоина на текущее время в долларах
     */
    public BigDecimal getBitcoinCurrentPriceUsd() {
        try {
            BigDecimal price = integrationService.getFieldValue(coinDeskServiceUrl,
                    "/bpi/USD/rate_float", BigDecimal.class);
            if (price == null) {
                throw new RuntimeException("Значение bitcoin usd равно null");
            }
            return price;
        } catch (Exception e) {
            throw new IntegrationException(e.getMessage());
        }
    }
}
