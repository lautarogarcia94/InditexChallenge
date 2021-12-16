package com.example.inditex.utils;

import com.example.inditex.entity.Prices;
import com.example.inditex.model.PriceIdentifier;

import java.time.LocalDateTime;

public class TestObjectBuilder {

    public static PriceIdentifier buildPriceIdentifier() {
        PriceIdentifier priceIdentifier = new PriceIdentifier();
        priceIdentifier.setApplicationDate("2020-06-15-16.00.00");
        priceIdentifier.setProductId(10);
        priceIdentifier.setBrandId(4);
        return priceIdentifier;
    }

    public static Prices buildPrice() {
        Prices price = new Prices();
        price.setId(123);
        price.setBrandId(5);
        price.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 8, 13, 23, 0, 0));
        price.setPriceList(1);
        price.setProductId(123456);
        price.setPriority(5);
        price.setPrice(455.50);
        price.setCurr("EUR");
        return price;
    }
}
