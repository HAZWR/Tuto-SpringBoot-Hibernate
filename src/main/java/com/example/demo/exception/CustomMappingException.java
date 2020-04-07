package com.example.demo.exception;

public class CustomMappingException  extends Exception {
    private static final String message = "An error during the data mapping happened";

    @Override
    public String getMessage() {
        return message;
    }

}
