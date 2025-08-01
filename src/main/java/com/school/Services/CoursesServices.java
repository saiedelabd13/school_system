package com.school.Services;

import com.school.Entity.Course;
import com.school.Repository.CoursesRepo;
import com.school.dto.CourseDTO;
import com.school.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServices {

    private final CoursesRepo coursesrepo;
    private final CourseMapper courseMapper;

    public CoursesServices(CoursesRepo coursesrepo, CourseMapper courseMapper) {
        this.coursesrepo = coursesrepo;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> findAllCourses() {
        List<Course> courses = coursesrepo.findAll();
        return courseMapper.toDTOList(courses);
    }

    public CourseDTO insertCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course saveCourse = coursesrepo.save(course);
        return courseMapper.toDto(saveCourse);
    }


    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = coursesrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        course.setTitle(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
        course.setLevel(courseDTO.getLevel());
        course.setFee(courseDTO.getFee());
        course.setInstructors(courseDTO.getInstructors());


        Course updated = coursesrepo.save(course);
        return courseMapper.toDto(updated);
    }


    public void deleteCourse(Long courseId) {
        coursesrepo.deleteById(courseId);
    }

    public CourseDTO findCourseById(Long courseId) {
        Course course = coursesrepo.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course not found with id: " + courseId));
        return courseMapper.toDto(course);
    }

}


