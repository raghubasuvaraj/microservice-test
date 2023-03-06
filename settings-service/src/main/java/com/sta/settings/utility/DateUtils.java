package com.sta.settings.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate convertStringToJustLocalDate(String dateInString, String dateFormat) {
        return LocalDate.parse(dateInString ,DateTimeFormatter.ofPattern(dateFormat));
    }
}
