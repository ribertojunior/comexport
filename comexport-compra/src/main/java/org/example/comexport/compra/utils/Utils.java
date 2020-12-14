package org.example.comexport.compra.utils;

import org.example.comexport.jpa.model.Orders;

import java.math.BigDecimal;

public class Utils {
  public static boolean validaOrder(Orders order) {
    return order != null
        && order.getPrice() != null && order.getPrice().compareTo(BigDecimal.ZERO) > 0
        && order.getCanalDeVenda() != null;
  }
}
