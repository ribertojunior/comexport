package org.example.comexport.motor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.comexport.jpa.repositories")
@EntityScan(basePackages = "org.example.comexport.jpa.model")
public class MotorApplication {
  public static void main(String[] args) {
    SpringApplication.run(MotorApplication.class, args);
  }
}
