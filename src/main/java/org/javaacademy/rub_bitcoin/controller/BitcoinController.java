package org.javaacademy.rub_bitcoin.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.rub_bitcoin.dto.BitcoinHistoryDto;
import org.javaacademy.rub_bitcoin.service.BitcoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
public class BitcoinController {
    private final BitcoinService bitcoinService;

    @PostMapping("/now")
    public BigDecimal getBitcoinInRubles() {
        return bitcoinService.getBitcoinPriceInRubles();
    }

    @GetMapping("/history")
    public List<BitcoinHistoryDto> getHistory() {
        return bitcoinService.getHistory();
    }

    @GetMapping("/average")
    public BigDecimal getAverageBitcoinInPriceRub() {
        return bitcoinService.getAverage();
    }
}
