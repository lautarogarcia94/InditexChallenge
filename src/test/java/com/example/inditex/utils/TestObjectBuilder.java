package com.example.inditex.utils;

import com.example.inditex.entity.Prices;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;

import java.time.LocalDateTime;

public class TestObjectBuilder {

    public static PriceIdentifier buildPriceIdentifier() {
        return PriceIdentifier.builder()
                .applicationDate("2020-06-15-16.00.00")
                .brandId(4)
                .productId(10)
                .build();
    }

    public static Prices buildPrice() {
        return Prices.builder()
                .id(123)
                .brandId(5)
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 8, 13, 23, 0, 0))
                .priceList(1)
                .productId(123456)
                .priority(5)
                .price(455.5)
                .curr("EUR")
                .build();
    }

    public static SelectedPrice buildSelectedPrice() {
        return SelectedPrice.builder()
                .price(455.5)
                .priceList(1)
                .brandId(5)
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 8, 13, 23, 0, 0))
                .productId(123456)
                .build();
    }
}
