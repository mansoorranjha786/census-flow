package com.postwork.cursorflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "census validation request containing an array of employee records")
public class CensusRequest {

    @Schema(description = "List of employee records to validate abd transform")
    private List<EmployeeRequest> employees;
}
