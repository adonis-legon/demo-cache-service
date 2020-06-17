package com.example.democacheservice;

public class UserMissingException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserMissingException() {
        super("User is missing");
    }

}