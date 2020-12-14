package org.example.comexport.motor.core.impl;

import org.example.comexport.jpa.model.Enum.CanalDeVenda;
import org.example.comexport.jpa.model.Orders;
import org.example.comexport.jpa.model.Product;
import org.example.comexport.jpa.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MotorCalculoTest {

  @Test
  void processarPedido() {
    MotorCalculo motorCalculo = new MotorCalculo();
    Orders order = new Orders();
    try {      
      motorCalculo.processarPedido(order);
      fail();
    } catch (Exception ignored) {
    }
    try {
      order.setUser(new User("Mano", "mano@mano.com.br"));
      motorCalculo.processarPedido(order);
      fail();
    } catch (Exception ignored){
    }
    try {
      Product product = new Product("Glass", new BigDecimal("3.1"));
      order.setProduct(product);
      order.setPrice(product.getPrice());
      motorCalculo.processarPedido(order);
      fail();

    } catch (Exception ignored) {
    }
    try {
      order.setCanalDeVenda(CanalDeVenda.E_COMMERCE);
      motorCalculo.processarPedido(order);
      System.out.println(order);
    } catch (Exception ignored) {
      fail();
    }
  }
}