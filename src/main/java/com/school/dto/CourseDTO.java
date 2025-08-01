package com.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.Entity.Course;
import com.school.Entity.Instructor;
import com.school.Entity.Registration;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CourseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Min(value = 0, message = "courses duration >= 0 hours/weeks")
    private Integer duration;

    private Course.Level level = Course.Level.BEGINNER;

    @Min(value = 0, message = "courses fee >= 0")
    private Double fee;

    private Set<Instructor> instructors = new HashSet<>();

    @JsonIgnore
    private Set<Registration> registrations = new HashSet<>();

    public enum Level {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}
