package io.matt.number.domain;

import io.matt.number.validator.Validator;

public class ValidatorResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }

    private boolean valid;

    public ValidatorResponse(){ }

    public ValidatorResponse(String message, boolean valid){
        this.message = message;
        this.valid = valid;
    }
}
