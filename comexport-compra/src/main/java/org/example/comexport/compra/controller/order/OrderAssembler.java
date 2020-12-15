package org.example.comexport.compra.controller.order;

import org.example.comexport.jpa.model.Orders;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler implements RepresentationModelAssembler<Orders, EntityModel<Orders>> {
  @Override
  public EntityModel<Orders> toModel(Orders entity) {
    return EntityModel.of(entity, linkTo(methodOn(OrderController.class).one(entity.getId())).withSelfRel(),
        linkTo(methodOn(OrderController.class).all()).withRel("orders"));
  }
}
