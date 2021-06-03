package com.mytest.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RequestMethod {

    private final RestTemplate restTemplate = new RestTemplate();

    public Object send(String url, Map<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> map : params.entrySet()) {
            multiValueMap.add(map.getKey(), map.getValue());
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(multiValueMap, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }

        return response != null ? response.getBody() : null;
    }
}