package ru.yandex.yandexlavka.controller.error;

public class TooManyRequestsException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Too many requests. RPS limit: ";

    public TooManyRequestsException(int limit) {
        super(DEFAULT_MESSAGE + limit);
    }
}
