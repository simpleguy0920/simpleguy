package com.java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println(instant.toEpochMilli());
        System.out.println(instant.getEpochSecond());
        Clock clock = Clock.systemUTC();
        System.out.println(clock.millis());
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getDayOfYear());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        localDateTime.plusDays(1);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        LocalDateTime localDateTime1 = LocalDateTime.parse("2018-11-11 22:22:22", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Duration duration = Duration.between(localDateTime1, localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

    }
}
