package com.postwork.cursorflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Summary statistics for processed census")
public class CensusSummary {

    @Schema(description = "Total number of employee submitted", example = "10")
    private int totalEmployee;

    @Schema(description = "Total number of eligible employee", example = "10")
    private int eligibleCount;

    @Schema(description = "Total number of ineligible employee(under 18 or above 65)", example = "1")
    private int ineligibleCount;
    @Schema(description = "Number of record with validation error", example = "1")
    private int validationErrorCount;
    @Schema(description = "Number of tobacco user among valid records", example = "3")
    private int tobaccoUserCount;
}
