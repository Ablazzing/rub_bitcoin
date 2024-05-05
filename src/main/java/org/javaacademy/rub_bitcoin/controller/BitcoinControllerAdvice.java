package org.javaacademy.rub_bitcoin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BitcoinControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handle(Exception e) {
        return ResponseEntity.internalServerError().body("На сайте наблюдаются проблемы, приходите позже");
    }
}
