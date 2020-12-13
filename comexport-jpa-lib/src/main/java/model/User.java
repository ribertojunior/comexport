package model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

  private @Id
  @GeneratedValue
  @OneToMany(targetEntity = Orders.class, mappedBy = "userId")
  Long id;
  @NonNull
  private String name;
  @NonNull
  private String email;
  private Date birthday;
  private DateRecord dateRecord;
  boolean enabled = true;
}
