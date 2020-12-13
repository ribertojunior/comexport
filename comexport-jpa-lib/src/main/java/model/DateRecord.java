package model;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@AllArgsConstructor
public class DateRecord {
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date createdAt;
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date updatedAt;
}
