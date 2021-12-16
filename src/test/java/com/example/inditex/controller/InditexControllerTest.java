package com.example.inditex.controller;

import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;
import com.example.inditex.service.database.DatabaseService;
import com.example.inditex.service.marshaller.MarshallerService;
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
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InditexControllerTest {

    private PriceIdentifier priceIdentifier;
    private SelectedPrice selectedPrice;

    @InjectMocks
    private InditexController inditexController;

    @Mock
    private ValidationService mockDateValidationService;

    @Mock
    private DatabaseService mockDatabaseService;

    @Mock
    private MarshallerService mockMarshallerService;

    @BeforeAll
    void setUp() {
        openMocks(this);

        priceIdentifier = TestObjectBuilder.buildPriceIdentifier();
        selectedPrice = TestObjectBuilder.buildSelectedPrice();
    }

    @Test
    void shouldReturnSuccessfully() throws ValidationException {
        String marshallMessage = "{price marshalled string}";
        doNothing().when(mockDateValidationService).validate(priceIdentifier);
        when(mockDatabaseService.getPrice(priceIdentifier)).thenReturn(selectedPrice);
        when(mockMarshallerService.marshallPrice(selectedPrice)).thenReturn(marshallMessage);

        ResponseEntity<String> response = inditexController.getProductPrice(priceIdentifier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(marshallMessage, response.getBody());
    }

    @Test
    void shouldFailProcessWhenValidationExceptionOccurs() throws ValidationException {
        String message = "Application date is not valid";
        doThrow(new ValidationException("Application date is not valid")).when(mockDateValidationService).validate(priceIdentifier);
        ResponseEntity<String> response = inditexController.getProductPrice(priceIdentifier);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void shouldFailProcessWhenMarshallerReturnsNull() throws ValidationException {
        String message = "Something went wrong";
        doNothing().when(mockDateValidationService).validate(priceIdentifier);
        when(mockDatabaseService.getPrice(priceIdentifier)).thenReturn(selectedPrice);
        when(mockMarshallerService.marshallPrice(selectedPrice)).thenReturn(null);

        ResponseEntity<String> response = inditexController.getProductPrice(priceIdentifier);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(message, response.getBody());
    }
}
