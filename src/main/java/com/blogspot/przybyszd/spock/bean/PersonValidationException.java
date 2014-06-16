package com.blogspot.przybyszd.spock.bean;

public class PersonValidationException extends RuntimeException {
    public PersonValidationException(String message) {
        super(message);
    }
}
