package org.example.comexport.compra.controller.product;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Long id) {
    super("Product not found" + id);
  }
}
