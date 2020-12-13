package org.example.comexport.jpa.repositories;

import org.example.comexport.jpa.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
  Orders findByUser(Long id);
}
