package org.example.comexport.compra.controller.product;

import lombok.AllArgsConstructor;
import org.example.comexport.jpa.model.Product;
import org.example.comexport.jpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class ProductController {

  @Autowired
  private final ProductRepository repository;

  @Autowired
  private final ProductAssembler assembler;

  @GetMapping("/products")
  CollectionModel<EntityModel<Product>> all() {
    List<EntityModel<Product>> products =
    repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
  }


  @GetMapping("/products/{id}")
  EntityModel<Product> one (@PathVariable Long id) {
    Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    return assembler.toModel(product);
  }
}
