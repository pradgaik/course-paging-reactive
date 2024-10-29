package org.example.persistencemodules.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

  @Id @GeneratedValue private Long id;
  private String name;

  private String description;

  private String instructorName;

  private Integer credits;

  private BigDecimal fee;

  @OneToMany(mappedBy = "courseEntity",fetch = FetchType.LAZY)
  private List<ReviewEntity> reviews = new ArrayList<>();

  private LocalDateTime createdDateTime;

  private LocalDateTime lastUpdatedDateTime;

  public void addReview(ReviewEntity reviewEntity) {
    reviews.add(reviewEntity);
  }

  public void removeReview(ReviewEntity reviewEntity) {
    reviews.remove(reviewEntity);
  }

}
