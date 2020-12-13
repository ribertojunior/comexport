package controller;

import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
public class UserController {
  @Autowired
  private UserService service;

  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {
    List<EntityModel<User>> users =
        service.retornaTodosUsuarios();
    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }

  @GetMapping("/exams/{id}")
  EntityModel<User> one(@PathVariable Long id) {
    User user = service.findById(id);
    return service.toModel(user);
  }
}
