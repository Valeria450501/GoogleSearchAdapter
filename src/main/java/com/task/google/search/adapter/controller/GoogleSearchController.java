package com.task.google.search.adapter.controller;

import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.service.GoogleSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/google-search")
@Tag(name = "REST API for google search requests")
public class GoogleSearchController {

    private GoogleSearchService gService;

    @GetMapping
    @Operation(
            summary = "Get google response",
            description = "Using googleapis performs search request and returns parsed response"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Response obtained successfully"
        ), @ApiResponse(
                responseCode = "400",
                description = "Wrong request format call"
        ), @ApiResponse(
                responseCode = "500",
                description = "Unable to receive google response"
    )})
    public ResponseEntity<SearchResponseDto> googleSearch(@RequestParam String searchQuery) {
        SearchResponseDto result = gService.searchByQuery(searchQuery);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
