package com.example.democacheservice.exceptions;

public class MissingUserException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MissingUserException() {
        super("User is missing");
    }

}