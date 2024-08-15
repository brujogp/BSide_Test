package com.soyjoctan.tests.bsidetest.expections;

import com.soyjoctan.tests.bsidetest.expections.customs.NoSuppliedIdStudentException;
import com.soyjoctan.tests.bsidetest.expections.customs.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<?> notFoundException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NoSuppliedIdStudentException.class)
    public final ResponseEntity<?> notSuppliedIdStudentException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> error = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> error.put("error", violation.getPropertyPath() + " " + violation.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}