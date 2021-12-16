package com.example.inditex.service.validation.impl;

import com.example.inditex.Utils.Utils;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.service.validation.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Service
public class DateValidationServiceImpl implements ValidationService {

    @Override
    public void validate(PriceIdentifier priceIdentifier) throws ValidationException {
        String applicationDate = priceIdentifier.getApplicationDate();
        DateTimeFormatter dateTimeFormatter = Utils.getDateTimeFormatter();

        try {
            dateTimeFormatter.parse(applicationDate);

        } catch (DateTimeParseException dateTimeParseExc) {
            String exceptionMessage = "Application date " + applicationDate + " is not a valid date";
            throw new ValidationException(exceptionMessage, dateTimeParseExc);
        }

        log.info("Application Date {} is a valid", applicationDate);
    }
}