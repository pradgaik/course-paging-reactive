package org.example.persistencemodules.service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import org.example.persistencemodules.entity.CourseEntity;
import org.example.persistencemodules.entity.StudentEntity;
import org.example.persistencemodules.mapper.StudentMapper;
import org.example.persistencemodules.model.PageRequestParam;
import org.example.persistencemodules.model.Student;
import org.example.persistencemodules.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  final StudentMapper studentMapper;

  final StudentRepository studentRepository;

  public StudentService(StudentMapper studentMapper, StudentRepository studentRepository ) {
      this.studentMapper = studentMapper;
      this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentMapper.studentEntityListToStudentList(studentRepository.findAll());
  }

  public Student getStudentById(Long id) {
    StudentEntity studentEntity= studentRepository.findById(id).orElse(null);
    return studentMapper.studentEntityToStudent(studentEntity);
  }

  public Page<Student> getStudentsWithPaginationAndSorting(PageRequestParam param) {
    Sort sort =
            Sort.by(
                    param.getDirection().equals("asc")
                            ? Sort.Order.asc(param.getSortBy())
                            : Sort.Order.desc(param.getSortBy()));
    Pageable pageable = PageRequest.of(param.getPageNumber(), param.getPageSize(), sort);
    Page<StudentEntity> page = studentRepository.findAll(pageable);
    return page.map(studentMapper::studentEntityToStudent);
  }

  /*public Page<Student> findAllWithPaginationAndSortingAndFilter(
          PageRequestParam param, String name) {
    Sort sort =
            Sort.by(
                    param.getDirection().equals("asc")
                            ? Sort.Order.asc(param.getSortBy())
                            : Sort.Order.desc(param.getSortBy()));
    Pageable pageable = PageRequest.of(param.getPageNumber(), param.getPageSize(), sort);

    Page<StudentEntity> page = studentRepository.findAllByNameContaining(name, pageable);
    return page.map(studentMapper::studentEntityToStudent);
  }*/

  public Student saveStudent(Student student) {
    StudentEntity studentEntity = studentMapper.studentToStudentEntity(student);
    if (studentEntity.getId() == null) {
      studentEntity.setCreatedDateTime(LocalDateTime.now());
      studentEntity.setLastUpdatedDateTime(LocalDateTime.now());
      return studentMapper.studentEntityToStudent(studentRepository.save(studentEntity));
    } else {
      StudentEntity existingStudentEntity =
          studentRepository
              .findById(studentEntity.getId())
              .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
      existingStudentEntity.setName(studentEntity.getName());
      existingStudentEntity.setLastUpdatedDateTime(LocalDateTime.now());
      return studentMapper.studentEntityToStudent(studentRepository.save(existingStudentEntity));
    }
  }

  public void deleteStudentById(Long id) {
    studentRepository.deleteById(id);
  }


}
