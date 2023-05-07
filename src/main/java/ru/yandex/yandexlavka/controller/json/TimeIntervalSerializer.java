package ru.yandex.yandexlavka.controller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TimeIntervalSerializer extends JsonSerializer<TimeInterval> {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void serialize(TimeInterval value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String stringValue = String.format(
                "%s-%s",
                DTF.format(value.getFrom()),
                DTF.format(value.getTo())
        );
        gen.writeString(stringValue);
    }
}
