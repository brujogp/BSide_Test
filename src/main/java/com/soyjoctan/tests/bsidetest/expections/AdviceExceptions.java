package com.soyjoctan.tests.bsidetest.expections;

import com.soyjoctan.tests.bsidetest.expections.customs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceExceptions {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<?> notFoundException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}