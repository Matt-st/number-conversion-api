package io.matt.number.service;

import io.matt.number.service.NumberConversionServiceImpl;
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




        assert("one vigintillion two hundred thirty four novemdecillion five hundred forty three octodecillion eight hundred ninety eight hundred seventy six sexdecillion three hundred forty one quindecillion two hundred thirty four quattuordecillion four hundred thirty five tredecillion six hundred twelve duodecillion three hundred forty two undecillion three hundred forty five decillion three hundred forty five nonillion eight hundred nine octillion eight hundred thirty four septillion seven hundred fifty nine hundred eighty seven quintillion two hundred thirty four quadrillion fifty nine trillion eight hundred seventy two billion three hundred four million five hundred ninety eight thousand seven hundred twelve".equals(impl.convertToString("1234543890876341234435612342345345809834750987234059872304598712")));

    }


}
