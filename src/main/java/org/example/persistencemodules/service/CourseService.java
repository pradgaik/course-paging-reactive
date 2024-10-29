package org.example.persistencemodules.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.persistencemodules.entity.CourseEntity;
import org.example.persistencemodules.mapper.CourseMapper;
import org.example.persistencemodules.model.Course;
import org.example.persistencemodules.model.PageRequestParam;
import org.example.persistencemodules.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

  final CourseMapper courseMapper;

  final CourseRepository courseRepository;

  public List<Course> getCourses() {
    return courseMapper.courseEntityListToCourseList(courseRepository.findAll());
  }

  public Page<Course> getCoursesWithPaginationAndSorting(PageRequestParam param) {
    Sort sort =
        Sort.by(
            param.getDirection().equals("asc")
                ? Sort.Order.asc(param.getSortBy())
                : Sort.Order.desc(param.getSortBy()));
    Pageable pageable = PageRequest.of(param.getPageNumber(), param.getPageSize(), sort);
    Page<CourseEntity> page = courseRepository.findAll(pageable);
    return page.map(courseEntity -> courseMapper.courseEntityToCourse(courseEntity));
  }

  public List<String> getItems(int pageNumber, int pageSize) {
    // Full dataset
    List<String> fullData = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6");

    // Pagination logic
    int start = pageNumber * pageSize;
    int end = Math.min(start + pageSize, fullData.size());

    // Return only a subset of items based on the page size
    return start < fullData.size() ? fullData.subList(start, end) : Arrays.asList();
  }

  public Page<Course> getCoursesWithPaginationAndSorting(
      PageRequestParam param, Long id, String name, String instructorName, Integer credits) {
    Sort sort =
        Sort.by(
            param.getDirection().equals("asc")
                ? Sort.Order.asc(param.getSortBy())
                : Sort.Order.desc(param.getSortBy()));
    Pageable pageable = PageRequest.of(param.getPageNumber(), param.getPageSize(), sort);
    Page<CourseEntity> page = null;
    if (id != null) {
      page = courseRepository.findById(id, pageable);
    } else {
      Specification<CourseEntity> spec = Specification.where(null);
      if (name != null)
        spec = spec.and(fieldLikeIgnoreCase("name", name));
      if (instructorName != null)
        spec = spec.and(fieldLikeIgnoreCase("instructorName", instructorName));
      if (credits != null)
        spec = spec.and(fieldEquals("credits", credits));
      page = courseRepository.findAll(spec, pageable);
    }
    try {
      Thread.sleep(2000);
      System.out.println("Task executed after delay");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return page.map(courseEntity -> courseMapper.courseEntityToCourse(courseEntity));
  }

  public Course getCourseById(Long id) {
    return courseMapper.courseEntityToCourse(courseRepository.findById(id).orElse(null));
  }
  public Course saveCourse(Course course) {
    CourseEntity courseEntity = courseMapper.courseToCourseEntity(course);
    if (courseEntity.getId() == null) {
      courseEntity.setCreatedDateTime(LocalDateTime.now());
      courseEntity.setLastUpdatedDateTime(LocalDateTime.now());
      return courseMapper.courseEntityToCourse(courseRepository.save(courseEntity));
    } else {
      CourseEntity existingCourseEntity =
              courseRepository
                      .findById(courseEntity.getId())
                      .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
      existingCourseEntity.setName(courseEntity.getName());
      existingCourseEntity.setLastUpdatedDateTime(LocalDateTime.now());
      return courseMapper.courseEntityToCourse(courseRepository.save(existingCourseEntity));
    }
  }

  public static <T> Specification<T> fieldLikeIgnoreCase(String fieldName, String searchTerm) {
    return (root, query, builder) ->
            builder.like(builder.lower(root.get(fieldName)), "%" + searchTerm.toLowerCase() + "%");
  }

  public static <T> Specification<T> fieldEquals(String fieldName, Integer searchTerm) {
    return (root, query, builder) ->
            builder.equal(root.get(fieldName), searchTerm);
  }

  public void deleteCourseById(Long id) {
    courseRepository.deleteById(id);
  }
}
