package com.postwork.cursorflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Raw employee data submitted by a broker")
public class EmployeeRequest {

    @Schema(description = "Employee full name", example = "Mansoor")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Date of birth in YYYY-MM-DD format", example = "1997-03-09")
    @JsonProperty("dob")
    private String dob;

    @Schema(description = "5-digit Zip Code", example = "10001")
    @JsonProperty("zipCode")
    private String zipCode;


    @Schema(description = "Tobacco use status (Accepts: Y, yes, true, N, no, false", example = "yes")
    @JsonProperty("tobaccoUse")
    private String tobaccoUse;
}
