package org.example.comexport.motor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderMalformedAdvice {
  @ResponseBody
  @ExceptionHandler(OrderMalformedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String examHandler(OrderMalformedException e) {
    return e.getMessage();
  }
}
