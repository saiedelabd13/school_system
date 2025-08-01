package com.school.Services;

import com.school.Entity.Registration;
import com.school.Repository.RegistrationRepo;
import com.school.Repository.StudentRepo;
import com.school.Repository.CoursesRepo;
import com.school.dto.RegistrationDTO;
import com.school.mapper.RegistrationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServices {

    private final RegistrationRepo registrationRepo;
    private final RegistrationMapper registrationMapper;
    private final StudentRepo studentRepo;
    private final CoursesRepo coursesRepo;

    public RegistrationServices(RegistrationRepo registrationRepo, RegistrationMapper registrationMapper, StudentRepo studentRepo, CoursesRepo coursesRepo) {
        this.registrationRepo = registrationRepo;
        this.registrationMapper = registrationMapper;
        this.studentRepo = studentRepo;
        this.coursesRepo = coursesRepo;
    }

    public List<RegistrationDTO> findAllRegistration() {
        List<Registration> registrations = registrationRepo.findAll();
        return registrationMapper.toDTOList(registrations);
    }

    public RegistrationDTO insertRegistration(RegistrationDTO registrationDTO) {
        Registration registration = registrationMapper.toEntity(registrationDTO);
        Registration saveRegistration = registrationRepo.save(registration);
        return registrationMapper.toDto(saveRegistration);
    }

    public RegistrationDTO updateRegistration(Long id, RegistrationDTO registrationDTO) {
        Registration registration = registrationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found with ID: " + id));
        registration.setStudent(registrationDTO.getStudent());
        registration.setCourse(registrationDTO.getCourse());
        registration.setRegistrationDate(registrationDTO.getRegistrationDate());
        registration.setPaymentStatus(registrationDTO.getPaymentStatus());


        Registration updated = registrationRepo.save(registration);
        return registrationMapper.toDto(updated);
    }


    public void deleteRegistration(Long registrationId) {
        registrationRepo.deleteById(registrationId);
    }

    public RegistrationDTO findRegistrationById(Long registrationId) {
        Registration registration = registrationRepo.findById(registrationId).orElseThrow();
        return registrationMapper.toDto(registration);
    }


    public void registerStudentToCourse(Long studentId, Long courseId) {
        Registration registration = new Registration();
        registration.setStudent(studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId)));
        registration.setCourse(coursesRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId)));
        registration.setRegistrationDate(java.time.LocalDateTime.now());
        registrationRepo.save(registration);
    }



}
