package org.example.comexport.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.comexport.jpa.repositories")
@EntityScan(basePackages = "org.example.comexport.jpa.model")
public class CadastroApplication {
  public static void main(String[] args) {
    SpringApplication.run(CadastroApplication.class, args);
  }
}
