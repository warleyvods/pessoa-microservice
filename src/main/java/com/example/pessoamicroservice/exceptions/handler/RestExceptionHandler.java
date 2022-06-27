package com.example.pessoamicroservice.exceptions.handler;

import com.example.pessoamicroservice.exceptions.PeopleNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PeopleNotFoundException.class)
    public ExceptionFilters handleUserNotFound(final PeopleNotFoundException ex) {
        return new ExceptionFilters("User not found!",
                NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getClass().getName()
        );
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionFilters handleArgumentNotValid(final MethodArgumentNotValidException ex) {
        return new ExceptionFilters("Argument not valid",
                NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getClass().getName()
        );
    }

}

