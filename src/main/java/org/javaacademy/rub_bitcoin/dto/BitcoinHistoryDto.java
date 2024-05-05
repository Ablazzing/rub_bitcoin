package org.javaacademy.rub_bitcoin.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class BitcoinHistoryDto {
    LocalDateTime time;
    BigDecimal price;
}
