package com.postwork.cursorflow.exception;

import java.util.List;

public class CensusValidationException extends RuntimeException {

    private final List<String> errors;

    public CensusValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
