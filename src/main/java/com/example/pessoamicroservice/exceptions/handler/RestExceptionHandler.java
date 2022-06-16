package com.example.pessoamicroservice.exceptions.handler;

import com.example.pessoamicroservice.exceptions.PeopleNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PeopleNotFoundException.class)
    public ExceptionFilters handleUserNotFound(final PeopleNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("User not found!")
                .build();
    }

}

