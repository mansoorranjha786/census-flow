package com.postwork.cursorflow.dto;


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
@Schema(description = "Census validation response with transformed data and summary")
public class CensusResponse {

    @Schema(description = "Processing status", example = "PROCESSED")
    private String status;
    @Schema(description = "Timestamp of processing")
    private LocalDateTime processedAt;
    @Schema(description = "Summary statistics for the census")
    private CensusSummary summary;
    @Schema(description = "List of transformed employee")
    private List<EmployeeResponse> employees;
}
