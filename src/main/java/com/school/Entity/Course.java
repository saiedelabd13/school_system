package com.school.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Table(name = "course")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;
    @Min(value = 0, message = "courses deuration >= 0  hours/weeks")
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private Level level = Level.BEGINNER;

    @Column(nullable = false)
    @Min(value = 0, message = "courses fee >= 0")
    private Double fee;

    @ManyToMany(mappedBy = "courses")
    private Set<Instructor> instructors = new HashSet<>();

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Registration> registrations = new HashSet<>();


    public enum Level {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}