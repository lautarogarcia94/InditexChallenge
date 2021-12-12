package com.example.inditex.controller;

import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.service.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RequiredArgsConstructor
@Slf4j
@RestController
public class InditexController {

    private final ValidationService dateValidationService;

    @GetMapping(value = "inditex/getProductPrice", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> getProductPrice(@Valid @RequestBody PriceIdentifier priceIdentifier) {
        int brandId = priceIdentifier.getBrandId();
        String applicationDate = priceIdentifier.getApplicationDate();
        int productId = priceIdentifier.getProductId();
        log.info("Request received. Brand ID: {}, application date: {}, product ID: {}", brandId, applicationDate, productId);

        try {
            dateValidationService.validate(priceIdentifier);
        } catch (ValidationException validationException) {
            log.error("Invalid application date: ", validationException);
            return new ResponseEntity<>(validationException.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Request has been received", HttpStatus.OK);
    }

}
