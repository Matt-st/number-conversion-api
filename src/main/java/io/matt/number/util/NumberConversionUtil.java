package io.matt.number.util;

public class NumberConversionUtil {

    public String removeLeadingZerosAndWhiteSpace(String number){
        String strPattern = "^0+(?!$)";
        String num =  number.replaceAll(strPattern, "");
        return num;
    }
}
