package io.matt.number.controller;

import io.matt.number.constants.ApiConstants;
import io.matt.number.service.NumberConversionService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NumberConversionControllerTest {

    @InjectMocks
    private NumberConversionController controller;

    @Mock
    private NumberConversionService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNumberConversionIsEmpty(){
        ResponseEntity<String> resp = controller.convert("");
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody().toString().equals(ApiConstants.EMPTY_OR_NULL_VALUE_ERROR));
    }

    @Test
    public void getNumberConversionIsNull(){
        ResponseEntity<String> resp = controller.convert(null);
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody().toString().equals(ApiConstants.EMPTY_OR_NULL_VALUE_ERROR));
    }

    @Test
    public void getNumberConversionIsOne(){
        when(service.convertToString(anyString())).thenReturn("one");
        ResponseEntity<String> resp = controller.convert("1");
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody().toString().equals("one"));
    }

    @Test
    public void getNumberConversionIsCurrency(){
        ResponseEntity<String> resp = controller.convert("$1");
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody().toString().contains("Currency not allowed"));
    }

    @Test
    public void getNumberConversionIsDecimal(){
        ResponseEntity<String> resp = controller.convert(".1");
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody().toString().contains("Decimal not allowed"));
    }

    @Test
    public void getNumberConversionIsFraction(){
        ResponseEntity<String> resp = controller.convert("/1");
        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody().toString().contains("Fraction not allowed"));
    }
}
