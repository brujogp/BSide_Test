package com.soyjoctan.tests.bsidetest.expections.customs;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}