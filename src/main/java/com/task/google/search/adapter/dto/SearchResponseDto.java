package com.task.google.search.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold google response for the search query"
)
public class SearchResponseDto {
    @Schema(
            description = "Response items list"
    )
    private List<SearchResponseItemDto> items;
}
