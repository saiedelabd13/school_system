package com.school.Services;

import com.school.Entity.Student;
import com.school.Repository.StudentRepo;
import com.school.dto.StudentsDTO;
import com.school.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    public StudentServices(StudentRepo studentRepo, StudentMapper studentMapper) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
    }

    public List<StudentsDTO> findAllStudent() {
        List<Student> students = studentRepo.findAll();
        return studentMapper.toDTOList(students);
    }

    public StudentsDTO insertStudent(StudentsDTO studentsDTO) {
        Student student = studentMapper.toEntity(studentsDTO);
        Student saveStudent = studentRepo.save(student);
        return studentMapper.toDto(saveStudent);
    }

    public StudentsDTO updateStudent(Long id, StudentsDTO studentsDTO) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        student.setName(studentsDTO.getName());
        student.setEmail(studentsDTO.getEmail());
        student.setAddress(studentsDTO.getAddress());
        student.setPhoneNumber(studentsDTO.getPhoneNumber());
        student.setRegistrations(studentsDTO.getRegistrations());

        Student updated = studentRepo.save(student);
        return studentMapper.toDto(updated);
    }


    public void deleteStudent(Long studentId) {
        studentRepo.deleteById(studentId);
    }

    public StudentsDTO findStudentById(Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        return studentMapper.toDto(student);
    }


}
