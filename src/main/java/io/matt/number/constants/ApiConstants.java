package io.matt.number.constants;

public class ApiConstants {
    public static final String ERROR = "Number parameter failed validation";
    public static final String CURRENCY_ERROR = ERROR.concat( " Currency not allowed");
    public static final String FRACTION_ERROR = ERROR + " Fraction not allowed";
    public static final String DECIMAL_ERROR = ERROR + " Decimal not allowed";
    public static final String NUMBER_TO_LARGE = ERROR + " Number to large to process.";
    public static final String EMPTY_OR_NULL_VALUE_ERROR = "Value is empty or null";
    public static final String SUCCESS = "ok";

}

