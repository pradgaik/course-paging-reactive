package org.example.persistencemodules.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.persistencemodules.model.Course;
import org.example.persistencemodules.model.PageRequestParam;
import org.example.persistencemodules.model.Student;
import org.example.persistencemodules.service.CourseService;
import org.example.persistencemodules.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class MyController {
  final CourseService courseService;
  final StudentService studentService;

  public MyController(CourseService courseService, StudentService studentService) {
    this.courseService = courseService;
    this.studentService = studentService;
  }

  @GetMapping("/courses")
  public ResponseEntity<List<Course>> getCourses(){
      List<Course> courses =courseService.getCourses();
      return ResponseEntity.ok(courses);
  }

  @GetMapping("/courses-pageable")
  @JsonProperty("courses")
  public ResponseEntity<Page<Course>> getCoursesWithPaginationAndSorting(
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "asc") String sortOrder,
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String instructorName,
      @RequestParam(required = false) Integer credits) {
    PageRequestParam pageRequestParam =
        new PageRequestParam(pageNumber, pageSize, sortBy, sortOrder);
    Page<Course> courses = courseService.getCoursesWithPaginationAndSorting(pageRequestParam, id, name, instructorName, credits);
    return ResponseEntity.ok(courses);
  }

  @JsonProperty("students")
  public ResponseEntity<Page<Student>> getStudentsWithPaginationAndSorting(
          @RequestParam(defaultValue = "0") int pageNumber,
          @RequestParam(defaultValue = "2") int pageSize,
          @RequestParam(defaultValue = "id") String sortBy,
          @RequestParam(defaultValue = "asc") String sortOrder) {
    PageRequestParam pageRequestParam =
            new PageRequestParam(pageNumber, pageSize, sortBy, sortOrder);
    Page<Student> students = studentService.getStudentsWithPaginationAndSorting(pageRequestParam);
    return ResponseEntity.ok(students);
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
    Student student = studentService.getStudentById(id);
    return ResponseEntity.ok(student);
  }

  @PostMapping("/students")
  public ResponseEntity<Student> saveStudent(@RequestBody Student Student){
    Student student = studentService.saveStudent(Student);
    return ResponseEntity.ok(student);
  }

  @DeleteMapping("/students/{id}")
  public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
    studentService.deleteStudentById(id);
    return ResponseEntity.ok().build();
  }
}
