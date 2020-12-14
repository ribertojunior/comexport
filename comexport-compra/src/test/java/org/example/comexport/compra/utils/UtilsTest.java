package org.example.comexport.compra.utils;

import org.example.comexport.jpa.model.Enum.CanalDeVenda;
import org.example.comexport.jpa.model.Orders;
import org.example.comexport.jpa.model.Product;
import org.example.comexport.jpa.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

  @Test
  void validaOrder() {
    Orders  order = new Orders();
    order.setPrice(new BigDecimal("1.1"));
    order.setCanalDeVenda(CanalDeVenda.E_COMMERCE);
    Product product = new Product("Glass", new BigDecimal("3.1"));
    product.setId(1L);
    order.setProduct(product);
    assertFalse(Utils.validaOrder(order));
    order.setUser(new User("Mano", "mano@mano.com.br"));
    assertTrue(Utils.validaOrder(order));
  }
}