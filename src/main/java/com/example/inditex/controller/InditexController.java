package com.example.inditex.controller;

import com.example.inditex.model.PriceIdentifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class InditexController {

    @GetMapping(value = "inditex/getProductPrice", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> getProductPrice(@Valid @RequestBody PriceIdentifier priceIdentifier){
        int brandId = priceIdentifier.getBrandId();
        String applicationDate = priceIdentifier.getApplicationDate();
        int productId = priceIdentifier.getProductId();
        log.info("Request received. Brand ID: {}, application date: {}, product ID: {}", brandId, applicationDate, productId);

        return new ResponseEntity<>("Request has been received",HttpStatus.OK);
    }

}
