package org.example.comexport.cadastro.controller;

import lombok.AllArgsConstructor;
import org.example.comexport.cadastro.utils.Utils;
import org.example.comexport.jpa.model.User;
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
public class UserController {

  @Autowired
  private final UserRepository repository;
  @Autowired
  private final UserAssembler assembler;

  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {
    List<EntityModel<User>> users =
        repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }

  @PostMapping("/users")
  ResponseEntity<?> newUser(@RequestBody User user) {
    if (Utils.validaUser(user)) {
      user.setCreatedAt(Calendar.getInstance().getTime());
      user.setUpdatedAt(user.getCreatedAt());
      User userSaved = repository.save(user);
      EntityModel<User> entityModel = assembler.toModel(userSaved);
      return ResponseEntity.created(
          linkTo(methodOn(UserController.class).one(userSaved.getId()))
              .withSelfRel()
              .toUri())
          .body(entityModel);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
        .body(Problem.create().withTitle("User error").withDetail("User data insufficient or malformed"));
  }

  @GetMapping("/users/{id}")
  EntityModel<User> one(@PathVariable Long id) {
    User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    return assembler.toModel(user);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<?> replaceUser(@RequestBody User novoUser, @PathVariable Long id) {
    if (Utils.validaUserReplace(novoUser)) {
      User user = repository.findById(id).map(user1 -> {
        user1.setName(novoUser.getName());
        user1.setBirthday(novoUser.getBirthday());
        user1.setUpdatedAt(Calendar.getInstance().getTime());
        return repository.save(user1);
      })
          .orElseGet(() -> {
                novoUser.setId(id);
                return repository.save(novoUser);
              }
          );
      EntityModel<User> entityModel = assembler.toModel(user);
      return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    } else {
      throw new UserException("Inconsistent User data");
    }
  }

  @DeleteMapping("/users/{id}")
  ResponseEntity<?> deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
