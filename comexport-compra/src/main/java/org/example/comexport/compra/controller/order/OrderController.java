package org.example.comexport.compra.controller.order;

import lombok.AllArgsConstructor;
import org.example.comexport.compra.controller.product.ProductNotFoundException;
import org.example.comexport.compra.utils.Utils;
import org.example.comexport.jpa.model.Orders;
import org.example.comexport.jpa.repositories.OrdersRepository;
import org.example.comexport.jpa.repositories.ProductRepository;
import org.example.comexport.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class OrderController {

  @Autowired
  private final OrdersRepository repository;

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final ProductRepository productRepository;

  @Autowired
  private final OrderAssembler assembler;

  @GetMapping("/orders")
  CollectionModel<EntityModel<Orders>> all() {
    List<EntityModel<Orders>> orders =
        repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
  }


  @PostMapping("orders/users/{user_id}/products/{prod_id}")
  ResponseEntity<?> newOrder(@RequestBody Orders order, @PathVariable Long user_id, @PathVariable Long prod_id) {
    if (Utils.validaOrder(order)) {
      Orders orderWithUser = userRepository.findById(user_id).map(user -> {
        order.setUser(user);
        return order;
      }).orElseThrow(() -> new OrderException("User id invalid."));
      Orders orderWithUserProduct = productRepository.findById(prod_id).map(product -> {
        orderWithUser.setProduct(product);
        return repository.save(orderWithUser);
      }).orElseThrow(() -> new ProductNotFoundException(prod_id));
      orderWithUserProduct.setCreatedAt(Calendar.getInstance().getTime());
      orderWithUserProduct.setUpdatedAt(orderWithUser.getCreatedAt());
      Orders orderSaved = repository.save(orderWithUserProduct);
      EntityModel<Orders> entityModel = assembler.toModel(orderSaved);
      return ResponseEntity.created(
          linkTo(methodOn(OrderController.class).one(orderSaved.getId()))
              .withSelfRel()
              .toUri())
          .body(entityModel);

    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        .body(Problem.create().withTitle("Order error").withDetail("Order data insufficient or malformed"));
  }

  @GetMapping("/orders/{id}")
  EntityModel<Orders> one(@PathVariable Long id) {
    Orders orders = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    return assembler.toModel(orders);
  }

  @PutMapping("/orders/{id}/users/{user_id}/products/{prod_id}")
  ResponseEntity<?> replaceOrder(@RequestBody Orders novaOrder, @PathVariable Long id, @PathVariable Long user_id, @PathVariable Long prod_id) {
    if (Utils.validaOrder(novaOrder)) {
      Orders orderWithUser = userRepository.findById(user_id).map(user -> {
        novaOrder.setUser(user);
        return novaOrder;
      }).orElseThrow(() -> new OrderException("User id invalid."));
      Orders orderWithUserProduct = productRepository.findById(prod_id).map(product -> {
        orderWithUser.setProduct(product);
        return orderWithUser;
      }).orElseThrow(() -> new ProductNotFoundException(prod_id));
      Orders order = repository.findById(id).map(order66 -> {
        order66.setUser(orderWithUserProduct.getUser());
        order66.setProduct(orderWithUserProduct.getProduct());
        order66.setCanalDeVenda(orderWithUserProduct.getCanalDeVenda());
        order66.setPrice(orderWithUserProduct.getPrice());
        order66.setUpdatedAt(Calendar.getInstance().getTime());
        return repository.save(order66);
      }).orElseGet(() -> {
        orderWithUserProduct.setId(id);
        return repository.save(orderWithUserProduct);
      });
      EntityModel<Orders> entityModel = assembler.toModel(order);
      return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    } else {
      throw new OrderException("Inconsistent Order Data");
    }
  }

  @DeleteMapping("/orders/{id}")
  ResponseEntity<?> deleteOrder(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
