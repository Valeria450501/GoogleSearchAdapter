package com.task.google.search.adapter.controller;

import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.service.GoogleSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/google-search")
public class GoogleSearchController {

    private GoogleSearchService gService;

    @GetMapping
    public ResponseEntity<SearchResponseDto> googleSearch(@RequestParam String searchQuery) {
        SearchResponseDto result = gService.searchByQuery(searchQuery);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
