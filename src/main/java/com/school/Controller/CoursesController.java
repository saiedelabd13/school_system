package com.school.Controller;

import com.school.Services.CoursesServices;
import com.school.dto.CourseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CoursesController {
    @Autowired
    public CoursesServices courses_services;


    @Operation(summary = "Insert a new course", description = "This endpoint allows you to add a new course to the system.")
    @PostMapping("/addCourses")
    public CourseDTO insertCourse(@RequestBody CourseDTO courseDTO) {
        return courses_services.insertCourse(courseDTO);
    }

    @Operation(summary = "Update an existing course", description = "This endpoint allows you to update an existing course by its ID.")
    @PutMapping("/updateCourses/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO updatedCourse) {
        return courses_services.updateCourse(id, updatedCourse);
    }

    @Operation(summary = "Delete a course", description = "This endpoint allows you to delete a course by its ID.")
    @DeleteMapping("/deleteCourse/{id}")
    public void deleteCourse(@PathVariable("id") Long courseId) {
        courses_services.deleteCourse(courseId);
    }

    @Operation(summary = "Get a course by ID", description = "This endpoint allows you to retrieve a course by its ID.")
    @GetMapping("/getCourses/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
        return courses_services.findCourseById(courseId);
    }

    @Operation(summary = "Find all courses", description = "This endpoint retrieves a list of all courses available in the system.")
    @GetMapping("/findAllCourses")
    public List<CourseDTO> findAllCourses() {
        return courses_services.findAllCourses();
    }

}
