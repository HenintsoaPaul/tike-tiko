package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterService {
    public String format(LocalDateTime localDateTime) {
        String pattern = "dd/MM/yyyy HH:mm";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }
}
