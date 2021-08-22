package com.aj.domain.bettingtypes;

import java.time.LocalDateTime;

public abstract class DateTimeParser {

    public LocalDateTime parse(String dateTimeString) {
        return LocalDateTime.parse(
                dateTimeString.substring(0, dateTimeString.length() - 1));
    }
}
