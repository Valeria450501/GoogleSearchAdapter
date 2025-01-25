package com.task.google.search.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResponseDto {
    private List<SearchResponseItemDto> items;
}
