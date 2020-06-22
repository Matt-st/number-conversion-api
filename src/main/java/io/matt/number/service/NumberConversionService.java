package io.matt.number.service;

import org.springframework.cache.annotation.Cacheable;

public interface NumberConversionService {

   // @Cacheable(value = "retrieveNumberConversionCache", key = "#number")
    public String convertToString(String number);
}
