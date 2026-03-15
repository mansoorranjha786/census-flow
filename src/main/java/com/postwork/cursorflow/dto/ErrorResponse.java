package com.postwork.cursorflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Error response for malformed or invalid requests")
public class ErrorResponse {

    @Schema(description = "Error response for malformed or invalid", example = "400")
    private int status;

    @Schema(description = "Human-readable error message", example = "Request payload contains validation errors")
    private String message;

    @Schema(description = "HTTP status code", example = "VALIDATION_ERROR")
    private String error;

    @Schema(description = "Timestamp of the error")
    private LocalDateTime timestamp;

    @Schema(description = "List of specified field-level error")
    private List<String> details;
}
