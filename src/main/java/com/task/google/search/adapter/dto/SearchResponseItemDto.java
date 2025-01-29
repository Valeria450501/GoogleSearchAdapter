package com.task.google.search.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response item",
        description = "Schema to hold successful response item information"
)
public class SearchResponseItemDto {

    @Schema(
            description = "Url", example = "https://en.wikipedia.org/wiki/Wiki"
    )
    private String url;

    @Schema(
            description = "Url description", example = "Wikis are powered by wiki software, also known as wiki engines."
    )
    private String description;
}
