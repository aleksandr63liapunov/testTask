package com.example.demo.controllerAdvice;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

//import java.util.NoSuchElementException;
//
//@RestControllerAdvice
//public class ControllerAdvice {
//    @ExceptionHandler(value = NoSuchElementException.class)
//    public ResponseEntity<BadRequest> noSuchElementException() {
//        return new ResponseEntity<>(new BadRequest(), HttpStatus.NOT_FOUND);
//    }
//
//    @Data
//    public class BadRequest {
//        private String name = "ba#id";
//    }
//}
