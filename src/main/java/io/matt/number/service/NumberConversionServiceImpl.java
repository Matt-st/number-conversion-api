package io.matt.number.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NumberConversionServiceImpl implements NumberConversionService {
    private final Logger log = LoggerFactory.getLogger(NumberConversionServiceImpl.class);

    private static final String[] SINGLE_DIGIT_WORDS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] SINGLE_WORDS = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] TEEN_DIGIT_WORDS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eightteen", "nineteen"};
    private static final String[] TENS_DIGIT_WORDS = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static final String[] LARGE_DIGIT_WORDS = {"","hundred", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion",
            "octillion", "nonillion", "decillion", "undecillion", "duodecillion", "tredecillion", "quattuordecillion", "quindecillion", "sexdecillion", "septendecillion",
            "octodecillion", "novemdecillion", "vigintillion", "centillion"};
    @Override
    public String convertToString(String number) {
        StringBuilder conversion = new StringBuilder();
        List<String> words = new ArrayList<>();
        if(number == null || number.isEmpty()){
            return "";
        }
        char[] nums = number.toCharArray();


        if(nums.length == 1){
            return SINGLE_DIGIT_WORDS[nums[0] - '0'];
        }

        log.info("Start conversion of number to string "+ number);
        for(int i = 0; i < nums.length; i++){
            if((nums.length - i)%3 == 1 && nums[i] - '0' != 0){
                words.add(SINGLE_WORDS[nums[i] - '0']);
                determineLargeDigitWord(words, nums, i);
            }else if((nums.length - i)%3 == 2 ){
                //check to see if it's greater than 1 which means it's not a teen number
                if(nums[i] - '0' > 1){
                    words.add(TENS_DIGIT_WORDS[(nums[i] - '0') - 2]);
                }else if( nums[i] - '0' == 1){
                    words.add(TEEN_DIGIT_WORDS[nums[i + 1] - '0']);
                    i++;
                    determineLargeDigitWord(words, nums, i);
                }

            }else if((nums.length - i)%3 == 0 && nums[i] - '0' != 0){
                words.add(SINGLE_WORDS[nums[i] - '0']);
                words.add(LARGE_DIGIT_WORDS[1]);
            }

        }
        for(String word : words){
            conversion.append(word).append(" ");
        }
        return conversion.toString().trim();
    }

    private void determineLargeDigitWord(List<String> words, char[] nums, int i) {
        if(nums.length - i == 4){//thousand
            words.add(LARGE_DIGIT_WORDS[2]);
        }else if(nums.length - i == 7){//million
            words.add(LARGE_DIGIT_WORDS[3]);
        }else if(nums.length - i == 10){//billion
            words.add(LARGE_DIGIT_WORDS[4]);
        }else if(nums.length - i == 13){//trillion
            words.add(LARGE_DIGIT_WORDS[5]);
        }else if(nums.length - i == 16){
            words.add(LARGE_DIGIT_WORDS[6]);
        }else if(nums.length - i == 19){
            words.add(LARGE_DIGIT_WORDS[7]);
        }else if(nums.length - i == 22){
            words.add(LARGE_DIGIT_WORDS[8]);
        }else if(nums.length - i == 25){
            words.add(LARGE_DIGIT_WORDS[9]);
        }else if(nums.length - i == 28){
            words.add(LARGE_DIGIT_WORDS[10]);
        }else if(nums.length - i == 31){
            words.add(LARGE_DIGIT_WORDS[11]);
        }else if(nums.length - i == 34){
            words.add(LARGE_DIGIT_WORDS[12]);
        }else if(nums.length - i == 37){
            words.add(LARGE_DIGIT_WORDS[13]);
        }else if(nums.length - i == 40){
            words.add(LARGE_DIGIT_WORDS[14]);
        }else if(nums.length - i == 43){
            words.add(LARGE_DIGIT_WORDS[15]);
        }else if(nums.length - i == 46){
            words.add(LARGE_DIGIT_WORDS[16]);
        }else if(nums.length - i == 49){
            words.add(LARGE_DIGIT_WORDS[17]);
        }else if(nums.length - i == 52){
            words.add(LARGE_DIGIT_WORDS[18]);
        }else if(nums.length - i == 55){
            words.add(LARGE_DIGIT_WORDS[19]);
        }else if(nums.length - i == 58){
            words.add(LARGE_DIGIT_WORDS[20]);
        }else if(nums.length - i == 61){
            words.add(LARGE_DIGIT_WORDS[21]);
        }else if(nums.length - i == 64){
            words.add(LARGE_DIGIT_WORDS[22]);
        }else if(nums.length - i == 67){
            words.add(LARGE_DIGIT_WORDS[23]);
        }

    }

}
