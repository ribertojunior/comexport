package org.example.comexport.jpa.model;

import lombok.*;
import org.example.comexport.jpa.model.Enum.CanalDeVenda;
import org.example.comexport.jpa.model.Enum.Status;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Orders {
  private @Id
  @GeneratedValue
  Long id;
  @NonNull
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "product_id")
  @NonNull
  private Product product;
  @NonNull
  private Status status;
  @NonNull
  private CanalDeVenda canalDeVenda;
  @NonNull
  private BigDecimal price;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date createdAt;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date updatedAt;

}
