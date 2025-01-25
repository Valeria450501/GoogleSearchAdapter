package com.task.google.search.adapter.service;

import com.task.google.search.adapter.config.GoogleSearchAppConfig;
import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.exception.GoogleResponseFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static com.task.google.search.adapter.mapper.SearchResponseItemMapper.mapToSearchDto;
@Service
public class GoogleSearchService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GoogleSearchAppConfig config;

    private static final String URL_PARAM_KEY           = "key";
    private static final String URL_PARAM_ENGINE_ID     = "cx";
    private static final String URL_PARAM_ALT           = "alt";
    private static final String URL_PARAM_QUERY         = "q";
    private static final String URL_PARAM_VALUE_JSON    = "json";


    public SearchResponseDto searchByQuery(String searchQuery) {
        ResponseEntity<LinkedHashMap> response = restTemplate.getForEntity(getSearchUrl(searchQuery), LinkedHashMap.class);

        if (Objects.requireNonNull(response.getBody()).get("items") instanceof List<?> items) {
            return mapToSearchDto(items);
        }

        throw new GoogleResponseFormatException();
    }

    private String getSearchUrl(String searchQuery) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(config.getSearchUrl())
                .queryParam(URL_PARAM_KEY, config.getApiKey())
                .queryParam(URL_PARAM_ENGINE_ID, config.getEngineId())
                .queryParam(URL_PARAM_ALT, URL_PARAM_VALUE_JSON)
                .queryParam(URL_PARAM_QUERY, searchQuery);
        return builder.toUriString();
    }

}
