package com.school;

import com.school.Entity.Course;
import com.school.Repository.CoursesRepo;
import com.school.Repository.InstructorRepo;
import com.school.Services.CoursesServices;
import com.school.Services.InstructorServices;
import com.school.Services.RegistrationServices;
import com.school.Services.StudentServices;
import com.school.dto.CourseDTO;
import com.school.dto.InstructorDTO;
import com.school.dto.StudentsDTO;
import com.school.mapper.CourseMapper;
import com.school.security.entity.AppUser;
import com.school.security.entity.Role;
import com.school.security.reposatory.RoleRepo;
import com.school.security.reposatory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@SpringBootApplication
@RestController
public class SchoolApplication {

    @Autowired
    private CoursesServices coursesServices;
    @Autowired
    private StudentServices studentServices;
    @Autowired
    private RegistrationServices registrationServices;
    @Autowired
    private InstructorServices instructorServices;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursesRepo coursesRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if data already exists
            if (coursesRepo.count() > 0) {
                System.out.println("Data already exists, skipping initialization...");
                return;
            }

            // add courses
            CourseDTO courseDTO1 = new CourseDTO();
            courseDTO1.setDescription("this is a course1");
            courseDTO1.setDuration(10);
            courseDTO1.setFee(100.0);
            courseDTO1.setLevel(Course.Level.BEGINNER);
            courseDTO1.setName("course1");
            CourseDTO savedCourse1 = coursesServices.insertCourse(courseDTO1);

            CourseDTO courseDTO2 = new CourseDTO();
            courseDTO2.setDescription("this is a course2");
            courseDTO2.setDuration(20);
            courseDTO2.setFee(200.0);
            courseDTO2.setLevel(Course.Level.INTERMEDIATE);
            courseDTO2.setName("course2");
            CourseDTO savedCourse2 = coursesServices.insertCourse(courseDTO2);

            CourseDTO courseDTO3 = new CourseDTO();
            courseDTO3.setDescription("this is a course3");
            courseDTO3.setDuration(30);
            courseDTO3.setFee(300.0);
            courseDTO3.setLevel(Course.Level.ADVANCED);
            courseDTO3.setName("course3");
            CourseDTO savedCourse3 = coursesServices.insertCourse(courseDTO3);


            // //////////////*************************/////////////////////////
            // add students
            StudentsDTO studentsDTO1 = new StudentsDTO();
            studentsDTO1.setName("Student 1");
            studentsDTO1.setEmail("student1@example.com");
            studentsDTO1.setPhoneNumber("1234567890");
            studentsDTO1.setAddress("Address 1");
            StudentsDTO savedStudent1 = studentServices.insertStudent(studentsDTO1);

            StudentsDTO studentsDTO2 = new StudentsDTO();
            studentsDTO2.setName("Student 2");
            studentsDTO2.setEmail("student2@example.com");
            studentsDTO2.setPhoneNumber("0987654321");
            studentsDTO2.setAddress("Address 2");
            StudentsDTO savedStudent2 = studentServices.insertStudent(studentsDTO2);

            StudentsDTO studentsDTO3 = new StudentsDTO();
            studentsDTO3.setName("Student 3");
            studentsDTO3.setEmail("student3@example.com");
            studentsDTO3.setPhoneNumber("1112223333");
            studentsDTO3.setAddress("Address 3");
            StudentsDTO savedStudent3 = studentServices.insertStudent(studentsDTO3);

            // ////////////////********************////////////////////////////////////
            //add instructors
            InstructorDTO instructorDTO1 = new InstructorDTO();
            instructorDTO1.setName("Instructor 1");
            instructorDTO1.setEmail("instructor1@example.com");
            instructorDTO1.setPhoneNumber("1234567890");
            InstructorDTO savedInstructor1 = instructorServices.insertInstructor(instructorDTO1);

            InstructorDTO instructorDTO2 = new InstructorDTO();
            instructorDTO2.setName("Instructor 2");
            instructorDTO2.setEmail("instructor2@example.com");
            instructorDTO2.setPhoneNumber("0987654321");
            InstructorDTO savedInstructor2 = instructorServices.insertInstructor(instructorDTO2);

            InstructorDTO instructorDTO3 = new InstructorDTO();
            instructorDTO3.setName("Instructor 3");
            instructorDTO3.setEmail("instructor3@example.com");
            instructorDTO3.setPhoneNumber("1112223333");
            InstructorDTO savedInstructor3 = instructorServices.insertInstructor(instructorDTO3);
///  /////////////////////////////////////////////////////////////////

            AppUser appUser1 = new AppUser();
            appUser1.setUsername("admin");
            appUser1.setPassword("admin123");

            AppUser appUser2 = new AppUser();
            appUser2.setUsername("user");
            appUser2.setPassword("user123");

            AppUser appUser3 = new AppUser();
            appUser3.setUsername("guest");


            Role adminRole = roleRepo.save(new Role("ADMIN"));
            Role userRole = roleRepo.save(new Role("USER"));

            AppUser user = new AppUser();
            user.setUsername("user1");
            user.setPassword(passwordEncoder.encode("password1"));
            //   user.setEnabled(true);
            user.setRoles(Set.of(userRole));
            userRepo.save(user);

            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            // admin.setEnabled(true);
            admin.setRoles(Set.of(adminRole));
            userRepo.save(admin);


        };
    }

    @Bean
    CommandLineRunner logActiveProfile() {
        return args -> {
            String activeProfile = environment.getProperty("spring.profiles.active");
            System.out.println("Active Profile: " + activeProfile);

            String authSecret = environment.getProperty("auth.secret");
            String accessExpiration = environment.getProperty("auth.access.expiration");
            String refreshExpiration = environment.getProperty("auth.refresh.expiration");

            System.out.println("auth.secret: " + authSecret);
            System.out.println("auth.access.expiration: " + accessExpiration);
            System.out.println("auth.refresh.expiration: " + refreshExpiration);
        };
    }
}
