package io.matt.number.service;

import org.junit.Ignore;
import org.junit.Test;

public class NumberConversionServiceImplTest {

    @Test
    public void convertSingleDigitTest(){
        NumberConversionServiceImpl impl = new NumberConversionServiceImpl();
        assert("zero".equals(impl.convertToString("0")));
        assert("one".equals(impl.convertToString("1")));
        assert("three".equals(impl.convertToString("3")));
        assert("five".equals(impl.convertToString("5")));
        assert("seven".equals(impl.convertToString("7")));
        assert("nine".equals(impl.convertToString("9")));

    }

    @Test
    public void convertTwoDigitTeensTest(){
        NumberConversionServiceImpl impl = new NumberConversionServiceImpl();
        assert("ten".equals(impl.convertToString("10")));
        assert("eleven".equals(impl.convertToString("11")));
        assert("thirteen".equals(impl.convertToString("13")));
        assert("fifteen".equals(impl.convertToString("15")));
        assert("seventeen".equals(impl.convertToString("17")));
        assert("nineteen".equals(impl.convertToString("19")));



    }

    @Test
    public void convertTwoDigitsTensTest(){
        NumberConversionServiceImpl impl = new NumberConversionServiceImpl();
        assert("thirty one".equals(impl.convertToString("31")));
        assert("twenty three".equals(impl.convertToString("23")));
        assert("fifty five".equals(impl.convertToString("55")));
        assert("sixty seven".equals(impl.convertToString("67")));
        assert("eighty nine".equals(impl.convertToString("89")));
    }

    @Test
    public void convertThreeDigitsTest(){
        NumberConversionServiceImpl impl = new NumberConversionServiceImpl();
        assert("three hundred eleven".equals(impl.convertToString("311")));
        assert("three hundred twenty eight".equals(impl.convertToString("328")));
        assert("five hundred".equals(impl.convertToString("500")));
        assert("eight hundred".equals(impl.convertToString("800")));
        assert("nine hundred".equals(impl.convertToString("900")));

    }

    @Test
    public void convertLargeDigitsTest(){
        NumberConversionServiceImpl impl = new NumberConversionServiceImpl();
        assert("three hundred".equals(impl.convertToString("300")));
        assert("one thousand five hundred".equals(impl.convertToString("1500")));
        assert("nine thousand four hundred twenty eight".equals(impl.convertToString("9428")));
        assert("twelve million three hundred forty five thousand six hundred seventy eight".equals(impl.convertToString("12345678")));
        assert("nine hundred ninety nine million eight hundred seventy six thousand one hundred eightteen".equals(impl.convertToString("999876118")));
        assert("nineteen million".equals(impl.convertToString("19000000")));
        assert("twenty eight thousand".equals(impl.convertToString("28000")));
        assert("nine million".equals(impl.convertToString("9000000")));
        assert("ten thousand five hundred".equals(impl.convertToString("10500")));
        assert("one hundred twenty three million four hundred fifty four thousand three hundred eighty nine".equals(impl.convertToString("0000000123454389")));
        assert("eight hundred ninety thousand".equals(impl.convertToString("890000")));
    }

    @Test
    public void largestNumber(){
        NumberConversionServiceImpl impl = new NumberConversionServiceImpl();
        assert("four hundred fifty six vigintillion seven hundred eighty nine novemdecillion twelve octodecillion three hundred forty five septendecillion six hundred seventy eight sexdecillion nine hundred one quindecillion two hundred thirty four quattuordecillion five hundred sixty seven tredecillion eight hundred ninety duodecillion one hundred twenty three undecillion four hundred fifty six decillion seven hundred eighty nine nonillion twelve octillion three hundred forty five septillion six hundred seventy eight sextillion nine hundred one quintillion two hundred thirty four quadrillion five hundred sixty seven trillion eight hundred ninety billion one hundred twenty three million four hundred fifty six thousand seven hundred eighty nine".equals(impl.convertToString("456789012345678901234567890123456789012345678901234567890123456789")));
    }


}
