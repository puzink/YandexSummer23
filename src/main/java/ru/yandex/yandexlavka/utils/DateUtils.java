package ru.yandex.yandexlavka.utils;

import ru.yandex.yandexlavka.entity.TimeInterval;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    public static TimeInterval parse(String s){
        Pattern p = Pattern.compile("((\\d{2}:\\d{2})-(\\d{2}:\\d{2}))");
        Matcher m = p.matcher(s);
        if(!m.matches() || m.groupCount() != 3){
            throw new IllegalArgumentException("Incorrect time interval format. Must be: \"HH:mm-HH:mm\". Got: " + s);
        }
        LocalTime from = LocalTime.parse(m.group(2), dtf);
        LocalTime to = LocalTime.parse(m.group(3), dtf);
        return new TimeInterval(from, to);
    }
}
