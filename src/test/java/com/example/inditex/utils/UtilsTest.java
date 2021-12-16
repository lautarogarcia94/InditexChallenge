package com.example.inditex.utils;

import com.example.inditex.Utils.Utils;
import com.example.inditex.entity.Prices;
import com.example.inditex.model.SelectedPrice;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void shouldReturnSelectedPriceOK() {
        Prices prices = TestObjectBuilder.buildPrice();
        SelectedPrice expectedSelectedPrice = TestObjectBuilder.buildSelectedPrice();

        SelectedPrice actualSelectedPrice = Utils.buildSelectedPrice(prices);

        assertEquals(expectedSelectedPrice.getBrandId(), actualSelectedPrice.getBrandId());
        assertEquals(expectedSelectedPrice.getPriceList(), actualSelectedPrice.getPriceList());
        assertEquals(expectedSelectedPrice.getPrice(), actualSelectedPrice.getPrice());
        assertEquals(expectedSelectedPrice.getProductId(), actualSelectedPrice.getProductId());
        assertEquals(expectedSelectedPrice.getEndDate(), actualSelectedPrice.getEndDate());
        assertEquals(expectedSelectedPrice.getStartDate(), actualSelectedPrice.getStartDate());
    }
}
