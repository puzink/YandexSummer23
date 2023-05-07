package ru.yandex.yandexlavka.controller.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeIntervalDeserializer extends JsonDeserializer<TimeInterval> {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");
    private static final Pattern INTERVAL_PATTERN = Pattern.compile("^(\\d{2}:\\d{2})-(\\d{2}:\\d{2})$");

    @Override
    public TimeInterval deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        String value = p.readValueAs(String.class);
        Matcher m = INTERVAL_PATTERN.matcher(value);
        if(!m.matches()){
            throw new IncorrectTimeIntervalFormatException(p, value);
        }

        try {
            LocalTime from = LocalTime.parse(m.group(1), DTF);
            LocalTime to = LocalTime.parse(m.group(2), DTF);
            return new TimeInterval(from, to);
        } catch (DateTimeParseException e){
            throw new IncorrectTimeIntervalFormatException(p, e, value);
        }
    }
}
