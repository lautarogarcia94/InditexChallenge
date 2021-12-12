package com.example.inditex.controller;

import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.service.validation.ValidationService;
import com.example.inditex.utils.TestObjectBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InditexControllerTest {

    private PriceIdentifier priceIdentifier;

    @InjectMocks
    private InditexController inditexController;

    @Mock
    private ValidationService mockDateValidationService;

    @BeforeAll
    void setUp() {
        openMocks(this);

        priceIdentifier = TestObjectBuilder.buildPriceIdentifier();
    }

    @Test
    void shouldReturnSuccessfully() throws ValidationException {
        String message = "Request has been received";
        doNothing().when(mockDateValidationService).validate(priceIdentifier);
        ResponseEntity<String> response = inditexController.getProductPrice(priceIdentifier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void shouldFailProcessWhenValidationExceptionOccurs() throws ValidationException {
        String message = "Application date is not valid";
        doThrow(new ValidationException("Application date is not valid")).when(mockDateValidationService).validate(priceIdentifier);
        ResponseEntity<String> response = inditexController.getProductPrice(priceIdentifier);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(message, response.getBody());
    }
}
