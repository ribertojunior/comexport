package org.example.comexport.cadastro.controller;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Long id) {
    super("User not Found: " + id);
  }
}
