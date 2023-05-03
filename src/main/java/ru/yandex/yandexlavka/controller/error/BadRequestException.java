package ru.yandex.yandexlavka.controller.error;

public class BadRequestException extends Throwable {

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
