package org.example.comexport.jpa.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

  boolean enabled = true;
  private @Id
  @GeneratedValue
  Long id;
  @NonNull
  private String name;
  @NonNull
  private String email;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date birthday;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date createdAt;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date updatedAt;
}
