package com.soyjoctan.tests.bsidetest.expections;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}