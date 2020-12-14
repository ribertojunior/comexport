package org.example.comexport.compra.controller.order;

public class OrderNotFoundException extends RuntimeException{
  public OrderNotFoundException(Long id) {
    super("Order not found: " + id);
  }
}
