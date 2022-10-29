package com.example.pessoamicroservice.exceptions.handler;

import java.time.LocalDateTime;

public record ExceptionFilters(
        String title,
        Integer status,
        String details,
        LocalDateTime timestamp,
        String devMsg
) {

}
