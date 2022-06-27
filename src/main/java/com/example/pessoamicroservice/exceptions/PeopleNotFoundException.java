package com.example.pessoamicroservice.exceptions;

public class PeopleNotFoundException extends RuntimeException {
    public PeopleNotFoundException(final String msg) {
        super(msg);
    }
}
