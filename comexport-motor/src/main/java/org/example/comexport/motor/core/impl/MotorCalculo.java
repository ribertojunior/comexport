package org.example.comexport.motor.core.impl;

import lombok.Data;
import org.example.comexport.compra.utils.Utils;
import org.example.comexport.jpa.model.Enum.Status;
import org.example.comexport.jpa.model.Orders;
import org.example.comexport.motor.controller.OrderMalformedException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Repository
public class MotorCalculo implements org.example.comexport.motor.core.IMotorCalculo {

  @Override
  public Orders processarPedido(Orders novaOrder) throws Exception {
    if (Utils.validaOrder(novaOrder)) {
      switch (novaOrder.getCanalDeVenda()) {
        case E_COMMERCE:
          novaOrder.setPrice(
              novaOrder.getProduct().getPrice()
                  .multiply(new BigDecimal("1.0537")).setScale(4, RoundingMode.HALF_EVEN));
          novaOrder.setStatus(Status.AGUARDANDO_ENTREGA);
          break;
        case PARCEIROS:
          novaOrder.setPrice(
              novaOrder.getProduct().getPrice()
                  .multiply(new BigDecimal("1.0878")).add(new BigDecimal("10.34").setScale(4, RoundingMode.HALF_EVEN)));
          novaOrder.setStatus(Status.AGUARDANDO_RETIRADA_PARCEIRO);
          break;
        case LOJA_FISICA:
          novaOrder.setStatus(Status.ENTREGUE);
          break;
      }
      return novaOrder;
    } else {
      throw new OrderMalformedException("Order malformed.");
    }

  }
}
