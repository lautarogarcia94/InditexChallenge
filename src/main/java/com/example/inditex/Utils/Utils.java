package com.example.inditex.Utils;

import com.example.inditex.constant.Constants;
import com.example.inditex.entity.Prices;
import com.example.inditex.model.SelectedPrice;

import java.time.format.DateTimeFormatter;

public class Utils {

    private Utils() {
        //This private constructor is to disable the default public constructor
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
    }

    public static SelectedPrice buildSelectedPrice(Prices prices) {
        return SelectedPrice.builder()
                .price(prices.getPrice())
                .priceList(prices.getPriceList())
                .brandId(prices.getBrandId())
                .endDate(prices.getEndDate())
                .productId(prices.getProductId())
                .startDate(prices.getStartDate())
                .build();
    }
}
