package com.example.demo.exception;

public class VehiculeNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Vehiculed not found.";
    }
}
