package org.example.comexport.motor.controller;

public class OrderMalformedException extends RuntimeException {
  public OrderMalformedException(String s) {
    super(s);
  }
}
