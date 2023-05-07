package ru.yandex.yandexlavka.controller.json;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.Closeable;

public class IncorrectTimeIntervalFormatException extends JsonMappingException {

    private static final String ERROR_MESSAGE_PATTERN =
            "Incorrect time interval format. Format must be: \"HH:mm-HH:mm\". Got: %s";

    public IncorrectTimeIntervalFormatException(Closeable processor, String interval) {
        super(processor, String.format(ERROR_MESSAGE_PATTERN, interval));
    }

    public IncorrectTimeIntervalFormatException(Closeable processor, Throwable problem, String interval) {
        this(processor,
                String.format(ERROR_MESSAGE_PATTERN, interval),
                problem);
    }

    public IncorrectTimeIntervalFormatException(Closeable processor, String msg, Throwable problem) {
        super(processor, msg, problem);
    }
}
