package com.school.Controller;

import com.school.Entity.Instructor;
import com.school.Services.InstructorServices;
import com.school.dto.InstructorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorControler {


    @Autowired
    private InstructorServices instructorService;

    @PostMapping("/addInstructor")
    private InstructorDTO insertInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.insertInstructor(instructorDTO);
    }

    @PutMapping("/updateinstructor/{id}")
    private InstructorDTO updateInstructor(@PathVariable Long id, @RequestBody InstructorDTO instructorDTO) {
        return instructorService.updateInstructor(id, instructorDTO);
    }

    @DeleteMapping("/deleteinstructor/{id}")
    private void deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
    }

    @GetMapping("/getinstructor/{id}")
    private InstructorDTO getInstructorById(@PathVariable Long id) {
        return instructorService.findInstructorById(id);
    }

    @GetMapping("/findAllInstructors")
    private List<InstructorDTO> findAllInstructors() {
        return instructorService.findAllCourses();
    }

    @PostMapping("/{id}/courses/{courseId}")
    private Instructor assignCourse(@PathVariable Long id, @PathVariable Long courseId) {
        return instructorService.assignCourseToInstructor(id, courseId);
    }

    @DeleteMapping("/{id}/courses/{courseId}")
    private Instructor removeCourse(@PathVariable Long id, @PathVariable Long courseId) {
        return instructorService.removeCourseFromInstructor(id, courseId);
    }

}
