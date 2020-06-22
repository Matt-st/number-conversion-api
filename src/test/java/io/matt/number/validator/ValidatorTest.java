package io.matt.number.validator;

import org.junit.Test;

public class ValidatorTest {

    @Test
    public void validatorTest(){
        Validator v = new Validator();
        assert(!v.isNumeric("$1234").isValid());
        assert(!v.isNumeric("3/4").isValid());
        assert(!v.isNumeric("12.34").isValid());
        assert(v.isNumeric("123").isValid());
        assert(!v.isNumeric("A").isValid());
        assert(!v.isNumeric("11111129A").isValid());
        assert(!v.isNumeric("").isValid());
        assert(v.isNumeric("00000124").isValid());
        assert(!v.isNumeric("0000    0124").isValid());

    }
}
