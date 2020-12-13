package model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
  private @Id
  @GeneratedValue
  @OneToMany(targetEntity = Orders.class, mappedBy = "productId")
  Long id;
  @NonNull private String description;
  @NonNull private BigDecimal price;
  private DateRecord dateRecord;
}
