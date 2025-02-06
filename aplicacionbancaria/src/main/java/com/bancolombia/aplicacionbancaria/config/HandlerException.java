package com.bancolombia.aplicacionbancaria.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity nullPointerException(NullPointerException  e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleException(IllegalArgumentException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity numberFormatException(IllegalStateException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <Map<String, String>> manejoValidaciones(MethodArgumentNotValidException e){
        Map<String, String> errores = new HashMap<>();
        e
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}
