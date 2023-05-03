package ru.yandex.yandexlavka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.yandexlavka.controller.error.BadRequestException;
import ru.yandex.yandexlavka.controller.error.NotFoundException;
import ru.yandex.yandexlavka.controller.response.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException e) throws JsonProcessingException {
        return addResponseBody(ResponseEntity.status(HttpStatus.NOT_FOUND), e);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequest(BadRequestException e) throws JsonProcessingException {
        return addResponseBody(ResponseEntity.status(HttpStatus.BAD_REQUEST), e);
    }

    private ResponseEntity<String> addResponseBody(ResponseEntity.BodyBuilder responseBody,
                                                   Throwable e) throws JsonProcessingException {
        ExceptionResponse response = new ExceptionResponse(e);
        String json = new ObjectMapper().writeValueAsString(response);
        return responseBody.body(json);
    }

}
