package com.stefan.buchalter.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String format(LocalDate date) {
        return FORMATTER.format(date);
    }

    public static LocalDate parse(String text) {
        return LocalDate.parse(text, FORMATTER);
    }
}
