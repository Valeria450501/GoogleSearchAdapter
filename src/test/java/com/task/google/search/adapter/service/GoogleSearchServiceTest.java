package com.task.google.search.adapter.service;

import com.task.google.search.adapter.config.GoogleSearchAppConfig;
import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.exception.GoogleResponseFormatException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GoogleSearchServiceTest {

    public static final String WIKI_TEST_RESPONSE_URL = "https://en.wikipedia.org/wiki/Wiki";
    public static final String WIKI_RESPONSE_DESCRIPTION = "Wikis are powered by wiki software, also known as wiki engines. Being a form of content management system, these differ from other web-based systems such as ...";
    public static final String QUERY_WIKI = "wiki";

    @MockitoBean
    private RestTemplate restTemplate;

    @Autowired
    private GoogleSearchAppConfig config;

    @Autowired
    private GoogleSearchService service;

    @Test
    @Disabled
    void testSearchByQuery() {
        String query = QUERY_WIKI;
        SearchResponseDto searchResponseDto = service.searchByQuery(query);
        assertNotNull(searchResponseDto);
    }

    @Test
    @Order(1)
    @DisplayName("Correct Response Mapping")
    void testCorrectResponseMapping() {
        when(restTemplate.getForEntity(service.getSearchUrl(QUERY_WIKI), LinkedHashMap.class))
                .thenReturn(getCorrectResponse());
        SearchResponseDto searchResponseDto = service.searchByQuery(QUERY_WIKI);
        assertNotNull(searchResponseDto, "Non null response should be obtained");
        assertNotNull(searchResponseDto.getItems(), "Non null items should be mapped");
        assertEquals(1, searchResponseDto.getItems().size(), "Response items should contain one element");
        assertEquals(WIKI_TEST_RESPONSE_URL, searchResponseDto.getItems().getFirst().getUrl(),
                "Correct url should be mapped to response item.");
        assertEquals(WIKI_RESPONSE_DESCRIPTION, searchResponseDto.getItems().getFirst().getDescription(),
                "Correct url should be mapped to response item.");
    }

    @Test
    @Order(2)
    @DisplayName("Incorrect Response Mapping")
    void testIncorrectResponseMapping() {
        when(restTemplate.getForEntity(service.getSearchUrl(QUERY_WIKI), LinkedHashMap.class))
                .thenReturn(new ResponseEntity<>(new LinkedHashMap<>(), HttpStatus.OK))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST))
                .thenReturn(new ResponseEntity<>(new LinkedHashMap<>() {{
                    put("items", new ArrayList<>() {{
                        put("", "");
                    }});
                }}, HttpStatus.OK));

        assertThrows(GoogleResponseFormatException.class, () -> service.searchByQuery(QUERY_WIKI),
                "Empty google response should be handled by calling proper exception");
        assertThrows(GoogleResponseFormatException.class, () -> service.searchByQuery(QUERY_WIKI),
                "Error google response should be handled by calling proper exception");
        assertTrue(service.searchByQuery(QUERY_WIKI).getItems().isEmpty(),
                "Google response with invalid items content should be empty");

    }

    @Test
    @Order(3)
    void testWrongGoogleUrl() {
        ReflectionTestUtils.setField(config, "searchUrl", "xxx");
        assertThrows(GoogleResponseFormatException.class, () -> service.searchByQuery(QUERY_WIKI),
                "Empty google response should be handled by calling proper exception");
    }

    private ResponseEntity<LinkedHashMap> getCorrectResponse() {
        return new ResponseEntity<>(new LinkedHashMap<>() {{
            put("items", new ArrayList<>() {{
                add(new LinkedHashMap() {{
                    put("link", WIKI_TEST_RESPONSE_URL);
                    put("snippet", WIKI_RESPONSE_DESCRIPTION);
                }});
            }});
        }}, HttpStatus.OK);
    }
}