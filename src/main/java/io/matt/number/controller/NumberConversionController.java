package io.matt.number.controller;

import io.matt.number.constants.ApiConstants;
import io.matt.number.domain.ValidatorResponse;
import io.matt.number.service.NumberConversionService;
import io.matt.number.service.NumberConversionServiceImpl;
import io.matt.number.validator.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="numberConversionController", description="Operation to convert a number to english language")
public class NumberConversionController {

    private final Logger LOG = LoggerFactory.getLogger(NumberConversionController.class);

    @Autowired
    NumberConversionService service;

    @RequestMapping(value = "/num_to_english", method = RequestMethod.GET)
    @ApiOperation(value = "Convert a number to english language",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully converted a string number to english language"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 422, message = "The entity is unprocessable and does not pass validation")
    })
    public ResponseEntity<String> convert(@RequestParam String number) {
        LOG.info("Number converted called with "+ number);
        Validator validator = new Validator();
        LOG.info("validator "+ validator.isNumeric(number).getMessage());
        ValidatorResponse valid = validator.isNumeric(number);
        if(!valid.isValid()){
            return new ResponseEntity<>(valid.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        String conversion = service.convertToString(number);
        if (conversion == null || conversion.isEmpty()) {
            return new ResponseEntity<>(conversion, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(conversion, HttpStatus.OK);
    }
}