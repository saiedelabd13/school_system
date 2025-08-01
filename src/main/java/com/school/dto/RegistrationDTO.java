package com.school.dto;

import com.school.Entity.Course;
import com.school.Entity.Registration;
import com.school.Entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class RegistrationDTO {
    private Student student;


    private Course course;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Registration.PaymentStatus paymentStatus = Registration.PaymentStatus.PENDING;

    public enum PaymentStatus {
        PENDING, PAID, REFUNDED, CANCELLED
    }
}
