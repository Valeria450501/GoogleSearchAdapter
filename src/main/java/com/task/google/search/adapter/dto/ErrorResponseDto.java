package com.task.google.search.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold unsuccessful response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "Error code", example = "500"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error message", example = "Google API response was not recognized"
    )
    private String errorMessage;
    @Schema(
            description = "Error code"
    )
    private LocalDateTime localDateTime;
}
