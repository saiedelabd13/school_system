package com.school.Services;

import com.school.Entity.Course;
import com.school.Entity.Instructor;
import com.school.Repository.CoursesRepo;
import com.school.Repository.InstructorRepo;
import com.school.dto.InstructorDTO;
import com.school.mapper.InstructorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServices {

    private final InstructorRepo instructorRepo;
    private final CoursesRepo coursesrepo;
    private final InstructorMapper instructorMapper;


    public InstructorServices(InstructorRepo instructorRepo, CoursesRepo coursesrepo, InstructorMapper instructorMapper) {
        this.instructorRepo = instructorRepo;
        this.coursesrepo = coursesrepo;
        this.instructorMapper = instructorMapper;
    }


    public List<InstructorDTO> findAllCourses() {
        List<Instructor> instructors = instructorRepo.findAll();
        return instructorMapper.toDTOList(instructors);
    }

    public InstructorDTO insertInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor saveInstructor = instructorRepo.save(instructor);
        return instructorMapper.toDto(saveInstructor);
    }

    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {
        Instructor instructor = instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        instructor.setCourses(instructorDTO.getCourses());

        Instructor updated = instructorRepo.save(instructor);
        return instructorMapper.toDto(updated);
    }

    public void deleteInstructor(Long instructorId) {
        instructorRepo.deleteById(instructorId);
    }

    public InstructorDTO findInstructorById(Long InstructorId) {
        Instructor instructor = instructorRepo.findById(InstructorId).orElseThrow(
                () -> new RuntimeException("Instructor not found with id: " + InstructorId));

        return instructorMapper.toDto(instructor);
    }

    /// ///////////////////////////////////////////////////////////
    public Instructor assignCourseToInstructor(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(
                () -> new RuntimeException("Instructor not found with id: " + instructorId));

        Course course = coursesrepo.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course not found with id: " + courseId));

        instructor.getCourses().add(course);
        return instructorRepo.save(instructor);
    }

    /// /////////////////////////////////////////////////////////////
    public Instructor removeCourseFromInstructor(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(
                () -> new RuntimeException("Instructor not found with id: " + instructorId));

        Course course = coursesrepo.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course not found with id: " + courseId));

        instructor.getCourses().remove(course);
        return instructorRepo.save(instructor);
    }
}
