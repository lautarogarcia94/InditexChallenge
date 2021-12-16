package com.example.inditex.controller;

import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;
import com.example.inditex.service.database.DatabaseService;
import com.example.inditex.service.marshaller.MarshallerService;
import com.example.inditex.service.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RequiredArgsConstructor
@Slf4j
@RestController
@Component
public class InditexController {

    private final ValidationService dateValidationService;
    private final DatabaseService databaseService;
    private final MarshallerService marshallerService;

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

        SelectedPrice selectedPrice = databaseService.getPrice(priceIdentifier);
        String marshalledPrice = marshallerService.marshallPrice(selectedPrice);
        log.info("The selected price is: {}", marshalledPrice);

        if (marshalledPrice == null) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(marshalledPrice, HttpStatus.OK);
        }
    }

}
