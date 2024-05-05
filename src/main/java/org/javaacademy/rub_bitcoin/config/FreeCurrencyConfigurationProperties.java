package org.javaacademy.rub_bitcoin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "bitcoin.integration.freecurrency")
@Configuration
@Getter @Setter
public class FreeCurrencyConfigurationProperties {
    private String url;
    private String apiKey;
    private String tokenHeaderName;
}
