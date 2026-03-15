package com.postwork.cursorflow.validation;

import com.postwork.cursorflow.dto.EmployeeRequest;
import com.postwork.cursorflow.service.TobaccoNormalizationService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CensusValidator {
    private final TobaccoNormalizationService tobaccoNormalizationService;

    public CensusValidator(TobaccoNormalizationService tobaccoNormalizationService) {
        this.tobaccoNormalizationService = tobaccoNormalizationService;
    }

    public List<String> validateEmployee(EmployeeRequest request) {
        List<String> errors = new ArrayList<>();
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            errors.add("Employee name is required");
        }

        if (request.getDob() == null || request.getDob().trim().isEmpty()) {
            errors.add("Date of birth is required");
        } else {
            try {
                LocalDate dob = LocalDate.parse(request.getDob().trim(), DateTimeFormatter.ISO_LOCAL_DATE);
                if (dob.isAfter(LocalDate.now())) {
                    errors.add("Date of birth cannot be in the future");
                }
            } catch (DateTimeParseException e) {
                errors.add("Invalid date of birth format. Expected YYYY-MM-DD, got: " + request.getDob());
            }
        }

        if (request.getZipCode() == null || request.getZipCode().trim().isEmpty()) {
            errors.add("Zip code is required");
        } else {
            String zip = request.getZipCode().trim();
            if (!zip.matches("^\\d{5}$")) {
                errors.add("Zip code must be 5-digit" + request.getZipCode());
            }
        }

        if (request.getTobaccoUse() != null && !request.getTobaccoUse().trim().isEmpty()) {
            if (!tobaccoNormalizationService.isRecognizedValue(request.getTobaccoUse())) {
                errors.add("Tobacco Use is not recognized " + request.getTobaccoUse());
            }
        }
        return errors;
    }

    public List<String> validateCensusRequest(Object request) {
        List<String> errors = new ArrayList<>();
        if (request == null) {
            errors.add("Request is null");
        }
        return errors;
    }
}
