package com.example.inditex.service.validation.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.inditex.constant.Constants;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.utils.TestObjectBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.LoggerFactory;

import javax.xml.bind.ValidationException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateValidationServiceImplTest {

    private PriceIdentifier priceIdentifier;
    private DateValidationServiceImpl dateValidationService;

    @BeforeAll
    void setUp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
        dateValidationService = new DateValidationServiceImpl(dateTimeFormatter);
        priceIdentifier = TestObjectBuilder.buildPriceIdentifier();
    }

    @Test
    void shouldValidateOK() throws ValidationException {
        Logger logger = (Logger) LoggerFactory.getLogger(DateValidationServiceImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        dateValidationService.validate(priceIdentifier);

        List<ILoggingEvent> logList = listAppender.list;
        assertEquals("Application Date {} is a valid", logList.get(0).getMessage());
        assertEquals(Level.INFO, logList.get(0).getLevel());
        assertEquals(1, logList.size());
    }

    @Test
    void shouldFailValidationWhenInvalidDate() {
        priceIdentifier.setApplicationDate("2021");

        Throwable exception = assertThrows(ValidationException.class, () -> dateValidationService.validate(priceIdentifier));
        String exceptionMessage = exception.getMessage();
        assertEquals("Application date 2021 is not a valid date", exceptionMessage);
    }
}
