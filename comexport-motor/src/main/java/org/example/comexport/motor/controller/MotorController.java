package org.example.comexport.motor.controller;

import lombok.AllArgsConstructor;
import org.example.comexport.jpa.model.Orders;
import org.example.comexport.motor.core.IMotorCalculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MotorController {

  @Autowired private final IMotorCalculo motorCalculo;

  @PostMapping("/motor")
  ResponseEntity<?> processaOrder(@RequestBody Orders novaOrder) {
    try {
      Orders orderProcessada = motorCalculo.processarPedido(novaOrder);
      return ResponseEntity.ok().body(orderProcessada);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
          .body(
              Problem.create()
                  .withTitle("Order error")
                  .withDetail("Order data insufficient or malformed"));
    }
  }
}
