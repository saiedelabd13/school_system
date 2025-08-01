package com.school.Controller;

import com.school.Services.StudentServices;
import com.school.dto.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentServices studentService;

    @PostMapping("/addStudent")
    private StudentsDTO addStudent(@RequestBody StudentsDTO studentsDTO) {
        return studentService.insertStudent(studentsDTO);
    }

    @PutMapping("/updateStudent/{id}")
    public StudentsDTO updateCourse(@PathVariable Long id, @RequestBody StudentsDTO studentsDTO) {
        return studentService.updateStudent(id, studentsDTO);
    }

    @DeleteMapping("/deleteStudent/{id}")
    private void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/findStudent/{id}")
    private StudentsDTO findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @GetMapping("findAllStudent")
    private List<StudentsDTO> findAllStudent() {
        return studentService.findAllStudent();
    }
}
