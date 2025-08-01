package com.school.dto;

import com.school.Entity.Registration;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter

public class StudentsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    private String address;


    private Set<Registration> registrations = new HashSet<>();
}

