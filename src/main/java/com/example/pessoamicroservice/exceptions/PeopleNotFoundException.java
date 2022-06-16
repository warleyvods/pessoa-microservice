package com.example.pessoamicroservice.exceptions;


public class PeopleNotFoundException extends RuntimeException{
    public PeopleNotFoundException(String msg) {
        super(msg);
    }
}
