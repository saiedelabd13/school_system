package com.school.dto;

import com.school.Entity.Course;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class InstructorDTO {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private Set<Course> courses = new HashSet<>();
}

