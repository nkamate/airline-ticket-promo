package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private DateUtils() {
    }

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = getSimpleDateFormat();
        return new Date(sdf.parse(dateString).getTime());
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = getSimpleDateFormat();
        return sdf.format(date);
    }

    private static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT);
    }
}
