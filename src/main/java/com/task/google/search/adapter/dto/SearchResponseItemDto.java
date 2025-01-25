package com.task.google.search.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResponseItemDto {
    private String url;
    private String description;
}
