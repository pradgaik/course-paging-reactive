package org.example.persistencemodules.mapper;

import org.example.persistencemodules.entity.CourseEntity;
import org.example.persistencemodules.entity.ReviewEntity;
import org.example.persistencemodules.model.Course;
import org.example.persistencemodules.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    public static CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    //@Mapping(target = "reviews", ignore = true)
    //@Mapping(target = "reviews", ignore = true)
    Course courseEntityToCourse(CourseEntity courseEntity);

    CourseEntity courseToCourseEntity(Course course);

    List<Course> courseEntityListToCourseList(List<CourseEntity> courseEntityList);

    List<Review> reviewEntityListToReviewList(List<ReviewEntity> reviewEntityList);

}
