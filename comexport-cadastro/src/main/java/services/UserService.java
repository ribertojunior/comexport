package services;

import controller.UserAssembler;
import controller.UserNotFoundException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserAssembler assembler;

  public List<EntityModel<User>> retornaTodosUsuarios(){
    return userRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
  }

  public User findById(Long id) throws UserNotFoundException {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  public EntityModel<User> toModel(User user) {
    return assembler.toModel(user);
  }
}
