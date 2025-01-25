package com.task.google.search.adapter.mapper;

import com.task.google.search.adapter.dto.SearchResponseDto;
import com.task.google.search.adapter.dto.SearchResponseItemDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchResponseItemMapper {

    public static SearchResponseDto mapToSearchDto(List<?> items) {
        return new SearchResponseDto(mapToSearchItemDto(items));
    }

    private static List<SearchResponseItemDto> mapToSearchItemDto(List<?> items) {
        return items.stream()
                .filter(i -> i instanceof Map<?, ?>)
                .map(i -> (Map<?, ?>) i)
                .map(i -> new SearchResponseItemDto(String.valueOf(i.get("link")), String.valueOf(i.get("snippet"))))
                .collect(Collectors.toList());
    }
}
