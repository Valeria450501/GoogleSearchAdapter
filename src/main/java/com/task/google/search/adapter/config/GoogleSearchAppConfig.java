package com.task.google.search.adapter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class GoogleSearchAppConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${google.search.uri}")
    private String searchUrl;

    @Value("${google.engine.id}")
    private String engineId;

    @Value("${google.api.key}")
    private String apiKey;
}
