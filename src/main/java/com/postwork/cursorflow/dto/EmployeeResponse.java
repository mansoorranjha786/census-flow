package com.postwork.cursorflow.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Transformed and validated employee data")
public class EmployeeResponse {

    @Schema(description = "Employee Full name", example = "Mansoor")
    private String name;

    @Schema(description = "Data of birth in ISO format", example = "1997-09-09")
    private String dob;

    @Schema(description = "Calculated insurance age", example = "38")
    private Integer insuranceAge;

    @Schema(description = "5-digit Zip Code",example = "10001")
    private String zipCode;

    @Schema(description = "Assigned rating area(1-4)",example = "1")
    private Integer ratingArea;

    @Schema(description = "Normalized tobacco use flag",example = "true")
    private Boolean tobaccoUse;

    @Schema(description = "Eligibility Status",example = "Eligible")
    private String eligibilityStatus;

    @Schema(description = "Reason for ineligibility if applicable",example = "Employee is under 18")
    private String ineligibilityReason;

    @Schema(description = "List of validation error for employee record")
    private List<String> validationErrors;
}
