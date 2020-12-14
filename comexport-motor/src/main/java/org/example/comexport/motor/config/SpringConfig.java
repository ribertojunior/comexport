package org.example.comexport.motor.config;

import org.example.comexport.compra.controller.order.OrderAssembler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public OrderAssembler orderAssemblerBean() { return new OrderAssembler();}
}
