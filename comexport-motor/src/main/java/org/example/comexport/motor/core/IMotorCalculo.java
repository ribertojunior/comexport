package org.example.comexport.motor.core;

import org.example.comexport.jpa.model.Orders;

public interface IMotorCalculo {

  Orders processarPedido(Orders novaOrders) throws Exception;
}
