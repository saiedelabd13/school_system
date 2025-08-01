package com.school.mapper;

import com.school.Entity.Course;
import com.school.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "title", target = "name")
    CourseDTO toDto(Course course);

    @Mapping(source = "name", target = "title")
    Course toEntity(CourseDTO dto);

    @Mapping(source = "title", target = "name")
    List<CourseDTO> toDTOList(List<Course> courses);
}
