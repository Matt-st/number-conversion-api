package io.matt.number.constants;

public class ApiConstants {
    public static final String NON_NUMBER_ERROR = "Number parameter failed validation";
    public static final String CURRENCY_ERROR = NON_NUMBER_ERROR.concat( " Currency not allowed");
    public static final String FRACTION_ERROR = NON_NUMBER_ERROR + " Fraction not allowed";
    public static final String DECIMAL_ERROR = NON_NUMBER_ERROR + " Decimal not allowed";
    public static final String UNKNOWN_VALUES_ERROR = NON_NUMBER_ERROR + " non numeric values not allowed";
    public static final String EMPTY_OR_NULL_VALUE_ERROR = "Value is empty or null";
    public static final String SUCCESS = "ok";

}
