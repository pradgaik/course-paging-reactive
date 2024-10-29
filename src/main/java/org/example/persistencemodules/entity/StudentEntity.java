package org.example.persistencemodules.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "passport_id")
  private PassportEntity passportEntity;

  private LocalDateTime createdDateTime;

  private LocalDateTime lastUpdatedDateTime;
}
