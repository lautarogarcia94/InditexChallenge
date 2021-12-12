package com.example.inditex.service.validation;

import com.example.inditex.model.PriceIdentifier;

import javax.xml.bind.ValidationException;

public interface ValidationService {

    void validate(PriceIdentifier priceIdentifier) throws ValidationException;
}
