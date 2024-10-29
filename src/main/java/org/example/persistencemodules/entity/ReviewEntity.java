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
@Table(name = "review")
public class ReviewEntity {

    @Id @GeneratedValue
    private Long id;

    private String rating;

    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

    private LocalDateTime createdDateTime;

    private LocalDateTime lastUpdatedDateTime;

}
