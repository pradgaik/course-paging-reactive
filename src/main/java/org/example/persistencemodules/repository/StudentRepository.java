package org.example.persistencemodules.repository;

import org.example.persistencemodules.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    //Page<StudentEntity> findAllByNameContaining(String name, Pageable pageable);
}
