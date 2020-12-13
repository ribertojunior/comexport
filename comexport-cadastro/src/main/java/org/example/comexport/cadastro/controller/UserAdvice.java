package org.example.comexport.cadastro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAdvice {

  @ResponseBody
  @ExceptionHandler(UserException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String examHandler(UserException e) {
    return e.getMessage();
  }

}
