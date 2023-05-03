package ru.yandex.yandexlavka.controller.response;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public ExceptionResponse(Throwable t){
        this.message = t.getMessage();
    }
}
