package com.rest_example.demo.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 1. Tells Spring: "Monitor all controllers. If something goes wrong, I'll handle it!"
public class GlobalExceptionHandler {

    // 2. This method will catch VALIDATION errors (e.g., empty fields)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        // Extract information from the error: which field failed and what the message was
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
            
        // Return status 400 (Bad Request) and the list of errors
        return ResponseEntity.badRequest().body(errors);
    }

    // 3. This method will catch an error if we search for a book by ID and it's NOT in the database
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        // Return status 404 (Not Found)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry, we don't have such a book.");
    }
}