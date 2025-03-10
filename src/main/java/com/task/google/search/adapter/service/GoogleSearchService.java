package com.task.google.search.adapter.service;

import com.task.google.search.adapter.config.GoogleSearchAppConfig;
import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.exception.GoogleResponseFormatException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;

import static com.task.google.search.adapter.mapper.SearchResponseItemMapper.mapToSearchDto;

@Service
@AllArgsConstructor
public class GoogleSearchService {

    private RestTemplate restTemplate;

    private GoogleSearchAppConfig config;

    private static final String URL_PARAM_KEY           = "key";
    private static final String URL_PARAM_ENGINE_ID     = "cx";
    private static final String URL_PARAM_ALT           = "alt";
    private static final String URL_PARAM_QUERY         = "q";
    private static final String URL_PARAM_VALUE_JSON    = "json";


    public SearchResponseDto searchByQuery(String searchQuery) {
        ResponseEntity<LinkedHashMap> response = restTemplate.getForEntity(getSearchUrl(searchQuery), LinkedHashMap.class);

        if (isSuccessfulResponse(response) && response.getBody().get("items") instanceof List<?> items) {
            return mapToSearchDto(items);
        }

        throw new GoogleResponseFormatException();
    }

    private boolean isSuccessfulResponse(ResponseEntity<LinkedHashMap> response) {
        return response != null && response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null;
    }

    protected String getSearchUrl(String searchQuery) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(config.getSearchUrl())
                .queryParam(URL_PARAM_KEY, config.getApiKey())
                .queryParam(URL_PARAM_ENGINE_ID, config.getEngineId())
                .queryParam(URL_PARAM_ALT, URL_PARAM_VALUE_JSON)
                .queryParam(URL_PARAM_QUERY, searchQuery);
        return builder.toUriString();
    }

}
