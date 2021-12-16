package com.example.inditex.Utils;

import com.example.inditex.constant.Constants;

import java.time.format.DateTimeFormatter;

public class Utils {

    private Utils() {
        //This private constructor is to disable the default public constructor
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
    }
}
