package com.example.inditex.utils;

import com.example.inditex.Utils.Utils;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.fail;

class UtilsTest {


    @Test
    void shouldReturnDateTimeFormatterWithCorrectPattern() {
        DateTimeFormatter dateTimeFormatter = Utils.getDateTimeFormatter();
        String dateTime = "2020-06-15-16.00.00";

        try {
            dateTimeFormatter.parse(dateTime);
        } catch (DateTimeParseException parseException) {
            fail("DateTimeFormatter should parse OK the DateTime: " + dateTime);
        }

    }
}
