package org.example.persistencemodules.mapper;

import java.util.List;
import org.example.persistencemodules.entity.StudentEntity;
import org.example.persistencemodules.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
  Student studentEntityToStudent(StudentEntity studentEntity);
  StudentEntity studentToStudentEntity(Student student);

  List<Student> studentEntityListToStudentList(List<StudentEntity> studentEntityList);
}
