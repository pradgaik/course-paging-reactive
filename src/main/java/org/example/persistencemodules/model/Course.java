package org.example.persistencemodules.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
  private Long id;
  private String name;
  private String description;
  private String instructorName;
  private Integer credits;
  private BigDecimal fee;
  private LocalDateTime createdDateTime;
  private LocalDateTime lastUpdatedDateTime;
  //private List<Review> reviews = new ArrayList<>();
}
