package com.postwork.cursorflow.controller;

import com.postwork.cursorflow.dto.CensusRequest;
import com.postwork.cursorflow.dto.CensusResponse;
import com.postwork.cursorflow.dto.ErrorResponse;
import com.postwork.cursorflow.exception.CensusValidationException;
import com.postwork.cursorflow.service.CensusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/census")
@Tag(name = "census Validation", description = "Endpoint for validating and transforming employee census data")
public class CensusController {

    private final CensusService censusService;

    public CensusController(CensusService censusService) {
        this.censusService = censusService;
    }

    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Validate and transform employee census data",
            description = "Accepts an array of employee objects, validates the data " +
                    "calculates insurance age, assigns rating areas, normalizes tobacco use " +
                    "and returns transformed data with eligibility status and summary statistics."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Census processed successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CensusResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Census request with employee records",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CensusRequest.class),
                    examples = @ExampleObject(
                            name = "Sample Census",
                            value = """
                                    {\s
                                    "employees": [
                                     {
                                      "name": "Jane Smith",
                                       "dob": "1990-05-15",
                                        "zipCode": "90210",
                                         "tobaccoUse": "no"\s
                                         },
                                          {
                                           "name": "John Doe",
                                            "dob": "2010-08-22",
                                             "zipCode": "91302",
                                              "tobaccoUse": "Y"\s
                                              },
                                               {\s
                                               "name": "Alice Johnson",
                                                "dob": "1955-01-10",
                                                 "zipCode": "10001",
                                                  "tobaccoUse": "false"\s
                                                  }\s
                                                  ]\s
                                                  }
                                   \s"""
                    )
            )
    )
    public ResponseEntity<CensusResponse> validateCensus(@RequestBody CensusRequest request) {
        List<String> errors = new ArrayList<>();

        if (request == null || request.getEmployees() == null) {
            errors.add("Request mist contain 'employee' array");
            throw new CensusValidationException("Request payload is missing", errors);
        }

        if (request.getEmployees().isEmpty()) {
            errors.add("The employees array must contain one employee record");
            throw new CensusValidationException("Empty employee list provided", errors);
        }

        CensusResponse response = censusService.processCensus(request);
        return ResponseEntity.ok(response);
    }
}
