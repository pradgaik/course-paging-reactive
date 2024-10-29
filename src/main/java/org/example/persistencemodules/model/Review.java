package org.example.persistencemodules.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.persistencemodules.entity.CourseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;

    private String rating;

    private String description;

    private Course course;

    private LocalDateTime createdDateTime;

    private LocalDateTime lastUpdatedDateTime;

}
