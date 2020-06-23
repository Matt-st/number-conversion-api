package io.matt.number.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NumberConversionServiceImpl implements NumberConversionService {
    private final Logger log = LoggerFactory.getLogger(NumberConversionServiceImpl.class);

    private static final String[] SINGLE_DIGIT_WORDS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] SINGLE_WORDS = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] TEEN_DIGIT_WORDS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eightteen", "nineteen"};
    private static final String[] TENS_DIGIT_WORDS = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    private static final Map<Integer, String> LARGE_DIGIT_WORDS_MAP;
    static {
        Map<Integer, String> initialMap = new HashMap<>();
        initialMap.put(0, "");
        initialMap.put(4, "thousand");
        initialMap.put(7, "million");
        initialMap.put(10, "billion");
        initialMap.put(13, "trillion");
        initialMap.put(16, "quadrillion");
        initialMap.put(19, "quintillion");
        initialMap.put(22, "sextillion");
        initialMap.put(25, "septillion");
        initialMap.put(28, "octillion");
        initialMap.put(31, "nonillion");
        initialMap.put(34, "decillion");
        initialMap.put(37, "undecillion");
        initialMap.put(40, "duodecillion");
        initialMap.put(43, "tredecillion");
        initialMap.put(46, "quattuordecillion");
        initialMap.put(49, "quindecillion");
        initialMap.put(52, "sexdecillion");
        initialMap.put(55, "septendecillion");
        initialMap.put(58, "octodecillion");
        initialMap.put(61, "novemdecillion");
        initialMap.put(64, "vigintillion");
        initialMap.put(67, "centillion");
        LARGE_DIGIT_WORDS_MAP = Collections.unmodifiableMap(initialMap);
    }

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
                words.add("hundred");
            }

        }
        for(String word : words){
            conversion.append(word).append(" ");
        }
        return conversion.toString().trim();
    }

    private void determineLargeDigitWord(List<String> words, char[] nums, int i) {
        if(LARGE_DIGIT_WORDS_MAP.containsKey(nums.length - i))
            words.add(LARGE_DIGIT_WORDS_MAP.get(nums.length - i));

    }

}
