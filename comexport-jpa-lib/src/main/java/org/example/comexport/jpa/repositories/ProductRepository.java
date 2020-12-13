package org.example.comexport.jpa.repositories;

import org.example.comexport.jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
