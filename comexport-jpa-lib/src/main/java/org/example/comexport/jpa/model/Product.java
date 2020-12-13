package org.example.comexport.jpa.model;

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
  Long id;
  @NonNull private String description;
  @NonNull private BigDecimal price;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date createdAt;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date updatedAt;
}
