package org.example.persistencemodules.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passport")
public class PassportEntity {

  @Id @GeneratedValue
  private Long id;

  private String passportNumber;

  private LocalDateTime createdDateTime;

  private LocalDateTime lastUpdatedDateTime;
}
