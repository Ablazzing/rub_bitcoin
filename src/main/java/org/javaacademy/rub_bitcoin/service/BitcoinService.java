package org.javaacademy.rub_bitcoin.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.rub_bitcoin.dto.BitcoinHistoryDto;
import org.javaacademy.rub_bitcoin.repository.BitcoinRepository;
import org.javaacademy.rub_bitcoin.service.integration.CoinDeskIntegrationService;
import org.javaacademy.rub_bitcoin.service.integration.FreeCurrencyIntegrationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.CEILING;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class BitcoinService {
    private final CoinDeskIntegrationService coinDeskIntegrationService;
    private final FreeCurrencyIntegrationService freeCurrencyIntegrationService;
    private final BitcoinRepository repository;

    public BigDecimal getBitcoinPriceInRubles() {
        BigDecimal bitcoinCurrentPriceUsd = coinDeskIntegrationService.getBitcoinCurrentPriceUsd();
        BigDecimal currentUsdPriceInRub = freeCurrencyIntegrationService.getCurrentUsdPriceInRub();
        BigDecimal bitcoinPriceInRubles = bitcoinCurrentPriceUsd.multiply(currentUsdPriceInRub);
        repository.save(now(), bitcoinPriceInRubles);
        return bitcoinPriceInRubles;
    }

    public List<BitcoinHistoryDto> getHistory() {
        return repository.getData().entrySet().stream()
                .map(entry -> convertToHistoryDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    public BigDecimal getAverage() {
        BigDecimal summa = repository.getData().values().stream()
                .reduce(ZERO, BigDecimal::add);
        int count = repository.getData().size();
        return summa.divide(BigDecimal.valueOf(count), 4, CEILING);
    }

    private BitcoinHistoryDto convertToHistoryDto(LocalDateTime time, BigDecimal value) {
        return new BitcoinHistoryDto(time, value);
    }


}
