package com.postwork.cursorflow.service;

import com.postwork.cursorflow.dto.*;
import com.postwork.cursorflow.validation.CensusValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CensusService {

    private final AgeCalculationService ageCalculationService;
    private final RatingAreaService ratingAreaService;
    private final TobaccoNormalizationService tobaccoNormalizationService;
    private final CensusValidator censusValidator;

    public CensusService(AgeCalculationService ageCalculationService, RatingAreaService ratingAreaService, TobaccoNormalizationService tobaccoNormalizationService, CensusValidator censusValidator) {
        this.ageCalculationService = ageCalculationService;
        this.ratingAreaService = ratingAreaService;
        this.tobaccoNormalizationService = tobaccoNormalizationService;
        this.censusValidator = censusValidator;
    }

    public CensusResponse processCensus(CensusRequest censusRequest) {
        List<EmployeeResponse> processedEmployee = new ArrayList<>();
        int eligibleCount = 0;
        int ineligibleCount = 0;
        int validationErrorCount = 0;
        int tobaccoUserCount = 0;

        for (EmployeeRequest employeeRequest : censusRequest.getEmployees()) {
            EmployeeResponse response = processEmployee(employeeRequest);
            processedEmployee.add(response);

            if (response.getValidationErrors() != null && !response.getValidationErrors().isEmpty()) {
                validationErrorCount++;
            } else if ("Ineligible".equals(response.getEligibilityStatus())) {
                ineligibleCount++;
            } else {
                eligibleCount++;
                if (Boolean.TRUE.equals(response.getTobaccoUse())) {
                    tobaccoUserCount++;
                }
            }
        }

        CensusSummary summary = CensusSummary.builder()
                .totalEmployee(censusRequest.getEmployees().size())
                .eligibleCount(eligibleCount)
                .ineligibleCount(ineligibleCount)
                .validationErrorCount(validationErrorCount)
                .tobaccoUserCount(tobaccoUserCount).build();

        return CensusResponse.builder()
                .status("PROCESSED")
                .processedAt(LocalDateTime.now())
                .summary(summary)
                .employees(processedEmployee).build();

    }

    private EmployeeResponse processEmployee(EmployeeRequest request) {
        List<String> errors = censusValidator.validateEmployee(request);

        if (!errors.isEmpty()) {
            return EmployeeResponse.builder()
                    .name(request.getName() != null ? request.getName().trim() : null)
                    .dob(request.getDob())
                    .zipCode(request.getZipCode())
                    .eligibilityStatus("Invalid")
                    .validationErrors(errors).build();
        }

        LocalDate dob = LocalDate.parse(request.getDob().trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate today = LocalDate.now();


        int insuranceAge = ageCalculationService.calculateInsuranceAge(dob, today);

        String zipCode = request.getZipCode().trim();

        int ratingArea = ratingAreaService.getRatingArea(zipCode);

        Boolean tobaccoUse = tobaccoNormalizationService.normalize(request.getTobaccoUse());

        boolean eligible = ageCalculationService.isEligibleByAge(insuranceAge);

        String eligibilityStatus = eligible ? "Eligible" : "Ineligible";

        String ineligibilityReason = ageCalculationService.getIneligibilityReason(insuranceAge);

        return EmployeeResponse.builder()
                .name(request.getName().trim())
                .dob(dob.toString())
                .insuranceAge(insuranceAge)
                .zipCode(zipCode)
                .ratingArea(ratingArea)
                .tobaccoUse(tobaccoUse)
                .eligibilityStatus(eligibilityStatus)
                .ineligibilityReason(ineligibilityReason).build();

    }
}
