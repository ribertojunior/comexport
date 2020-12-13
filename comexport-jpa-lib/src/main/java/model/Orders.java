package model;

import lombok.*;
import model.Enum.CanalDeVenda;
import model.Enum.Status;

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
  private Long userId;
  @ManyToOne
  @JoinColumn(name = "product_id")
  @NonNull
  private Long productId;
  @NonNull
  private Status status;
  @NonNull
  private CanalDeVenda canalDeVenda;
  @NonNull
  private BigDecimal price;
  private DateRecord dateRecord;

}
