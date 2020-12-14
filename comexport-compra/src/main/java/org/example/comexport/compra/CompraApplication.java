package org.example.comexport.compra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.comexport.jpa.repositories")
@EntityScan(basePackages = "org.example.comexport.jpa.model")
public class CompraApplication {
  public static void main(String[] args) {
    SpringApplication.run(CompraApplication.class, args);
  }
}
