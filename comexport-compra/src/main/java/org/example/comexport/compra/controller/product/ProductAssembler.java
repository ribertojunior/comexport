package org.example.comexport.compra.controller.product;

import org.example.comexport.jpa.model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
  @Override
  public EntityModel<Product> toModel(Product entity) {
    return EntityModel.of(entity, linkTo(methodOn(ProductController.class).one(entity.getId())).withSelfRel(),
        linkTo(methodOn(ProductController.class).all()).withRel("products"));
  }
}
