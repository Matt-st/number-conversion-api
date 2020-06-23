package io.matt.number.validator;

import io.matt.number.constants.ApiConstants;
import io.matt.number.domain.ValidatorResponse;
import io.matt.number.service.NumberConversionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class Validator {
    private final Logger log = LoggerFactory.getLogger(Validator.class);

    private Pattern digitPattern = Pattern.compile("^[0-9]+$");

    public ValidatorResponse isNumeric(String strNum) {
        log.info("Validating data for conversion...");

        if (strNum == null || strNum.isEmpty()) {
            return new ValidatorResponse(ApiConstants.EMPTY_OR_NULL_VALUE_ERROR, false);
        }else if(strNum.length() > 66){
            return new ValidatorResponse(ApiConstants.NUMBER_TO_LARGE, false);
        }else if(strNum.contains("$")){
            return new ValidatorResponse(ApiConstants.CURRENCY_ERROR, false);
        }else if(strNum.contains(".")){
            return new ValidatorResponse(ApiConstants.DECIMAL_ERROR, false);
        }else if(strNum.contains("/")){
            return new ValidatorResponse(ApiConstants.FRACTION_ERROR, false);
        }else if(!digitPattern.matcher(strNum).matches()){
            return new ValidatorResponse(ApiConstants.ERROR, false);
        }

        return new ValidatorResponse(ApiConstants.SUCCESS, true);
    }
}
