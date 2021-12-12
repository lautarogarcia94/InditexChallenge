package com.example.inditex.utils;

import com.example.inditex.model.PriceIdentifier;

public class TestObjectBuilder {

    public static PriceIdentifier buildPriceIdentifier() {
        PriceIdentifier priceIdentifier = new PriceIdentifier();
        priceIdentifier.setApplicationDate("2020-06-15-16.00.00");
        priceIdentifier.setProductId(10);
        priceIdentifier.setBrandId(4);
        return priceIdentifier;
    }
}
