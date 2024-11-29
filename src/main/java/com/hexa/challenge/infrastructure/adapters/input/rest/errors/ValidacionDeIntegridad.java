package com.hexa.challenge.infrastructure.adapters.input.rest.errors;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
