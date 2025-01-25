package com.task.google.search.adapter.service;

import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.dto.SearchResponseItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleSearchServiceTest {

    @Autowired
    GoogleSearchService service;

    @Test
    void testSearchByQuery() {
        String query = "wiki";
        SearchResponseDto searchResponseDto = service.searchByQuery(query);
        assertNotNull(searchResponseDto);
    }

}