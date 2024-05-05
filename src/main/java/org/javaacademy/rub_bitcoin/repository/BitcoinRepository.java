package org.javaacademy.rub_bitcoin.repository;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class BitcoinRepository {
    private final SortedMap<LocalDateTime, BigDecimal> data = new TreeMap<>();

    public void save(LocalDateTime time, BigDecimal price) {
        data.put(time, price);
    }

    public SortedMap<LocalDateTime, BigDecimal> getData() {
        return data;
    }
}
