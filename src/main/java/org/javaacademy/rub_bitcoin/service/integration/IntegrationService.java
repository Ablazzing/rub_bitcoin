package org.javaacademy.rub_bitcoin.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IntegrationService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @SneakyThrows
    public <T> T getFieldValue(String url, String path, Class<T> tClass) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return mapper.convertValue(
                mapper.readTree(response.getBody()).at(path).asText(), tClass);
    }

    @SneakyThrows
    public <T> T getFieldValue(String url,
                               String path,
                               Class<T> tClass,
                               String headerName,
                               String headerValue,
                               Map<String, String> requestParams) {
        RequestEntity<Void> request = RequestEntity.get(finalUrl(url, requestParams))
                .header(headerName, headerValue)
                .build();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return mapper.convertValue(
                mapper.readTree(response.getBody()).at(path).asText(), tClass);
    }

    private String finalUrl(String baseUrl, Map<String, String> requestParams) {
        return "%s?%s".formatted(baseUrl, createRequestParamsString(requestParams));
    }

    private String createRequestParamsString(Map<String, String> requestParams) {
        return requestParams.entrySet().stream()
                .map(entry -> "%s=%s".formatted(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("&"));
    }
}
