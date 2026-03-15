package com.postwork.cursorflow.service;


import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TobaccoNormalizationService {

    private static final Set<String> TRUE_VALUES = Set.of("y", "yes", "true", "1", "t", "smoker", "tobacco");
    private static final Set<String> FALSE_VALUES = Set.of("n", "no", "false", "0", "f", "non-smoker", "nonsmoker", "none");

    /**
     * @param input the raw tobacco user input
     * @return true if input tobacco indicate use, false if not use
     * @throws IllegalArgumentException if the value is invalid
     */

    public Boolean normalize(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }
        String cleaned = input.trim().toLowerCase();

        if (TRUE_VALUES.contains(cleaned)) {
            return true;
        }
        if (FALSE_VALUES.contains(cleaned)) {
            return false;
        }

        throw new IllegalArgumentException("Invalid input: " + input + "'. " + "Accepted values: Y,yes, true,1, N, no, false,0");
    }

    public boolean isRecognizedValue(String input) {
        if (input == null || input.isBlank()) {
            return true;
        }
        String cleaned = input.trim().toLowerCase();
        return TRUE_VALUES.contains(cleaned) || FALSE_VALUES.contains(cleaned);
    }
}
