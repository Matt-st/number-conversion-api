package io.matt.number.validator;

import io.matt.number.constants.ApiConstants;
import io.matt.number.domain.ValidatorResponse;

import java.util.regex.Pattern;

public class Validator {

    private Pattern digitPattern = Pattern.compile("^[0-9]+$");

    public ValidatorResponse isNumeric(String strNum) {
        if (strNum == null || strNum.isEmpty()) {
            return new ValidatorResponse(ApiConstants.EMPTY_OR_NULL_VALUE_ERROR, false);
        }else if(strNum.contains("$")){
            return new ValidatorResponse(ApiConstants.CURRENCY_ERROR, false);
        }else if(strNum.contains(".")){
            return new ValidatorResponse(ApiConstants.DECIMAL_ERROR, false);
        }else if(strNum.contains("/")){
            return new ValidatorResponse(ApiConstants.FRACTION_ERROR, false);
        }else if(!digitPattern.matcher(strNum).matches()){
            return new ValidatorResponse(ApiConstants.NON_NUMBER_ERROR, false);
        }

        return new ValidatorResponse(ApiConstants.SUCCESS, true);
    }
}
