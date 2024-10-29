package org.example.persistencemodules.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private Long id;

  private String name;

  private Passport passport;

  private LocalDateTime createdDateTime;

  private LocalDateTime lastUpdatedDateTime;
}
