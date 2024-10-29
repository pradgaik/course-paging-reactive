package org.example.persistencemodules.repository;

import org.example.persistencemodules.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Page<CourseEntity> findAll(Specification<CourseEntity> spec, Pageable pageable);
    Page<CourseEntity> findById(Long id, Pageable pageable);

}
