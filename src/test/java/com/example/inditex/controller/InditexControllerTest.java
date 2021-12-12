package com.example.inditex.controller;

import com.example.inditex.model.PriceIdentifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InditexControllerTest {

    private InditexController inditexController;
    private PriceIdentifier priceIdentifier;

    @BeforeAll
    void setUp() {
        inditexController = new InditexController();
        priceIdentifier = new PriceIdentifier();
        priceIdentifier.setApplicationDate("2020-06-15-16.00.00");
        priceIdentifier.setBrandId(10);
        priceIdentifier.setProductId(5);
    }

    @Test
    void shouldReturnSuccessfully() {
        String message = "Request has been received";
        ResponseEntity<String> response = inditexController.getProductPrice(priceIdentifier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }
}
