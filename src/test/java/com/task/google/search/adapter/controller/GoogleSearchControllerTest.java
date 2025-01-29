package com.task.google.search.adapter.controller;

import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.dto.SearchResponseItemDto;
import com.task.google.search.adapter.service.GoogleSearchService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;

import static com.task.google.search.adapter.service.GoogleSearchServiceTest.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GoogleSearchControllerTest {

    @MockitoBean
    private GoogleSearchService service;

    @Autowired
    private GoogleSearchController controller;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Disabled
    void testGoogle() {
        controller.googleSearch("Are you working?");
        System.out.println();
    }

    @Test
    @DisplayName("Google Search request with parameter")
    void getGoogleSearchResult() throws Exception {
        when(service.searchByQuery(QUERY_WIKI))
                .thenReturn(new SearchResponseDto(List.of(new SearchResponseItemDto(WIKI_TEST_RESPONSE_URL, WIKI_RESPONSE_DESCRIPTION))));

        mockMvc.perform(MockMvcRequestBuilders.get("/google-search")
                        .param("searchQuery", QUERY_WIKI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.items", hasSize(1)))
                .andExpect(jsonPath("$.items[0].url", is(WIKI_TEST_RESPONSE_URL)))
                .andExpect(jsonPath("$.items[0].description", is(WIKI_RESPONSE_DESCRIPTION)));
    }

    @Test
    @DisplayName("Google Search request without parameters")
    void getGoogleSearchResultNok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/google-search"))
                .andExpect(status().is4xxClientError());
    }

}