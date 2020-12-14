package org.example.comexport.compra.controller.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductAdvice {
  @ResponseBody
  @ExceptionHandler(ProductException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String examHandler(ProductException e) {
    return e.getMessage();
  }
}
