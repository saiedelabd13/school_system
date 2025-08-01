package com.school.Repository;

import com.school.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepo extends JpaRepository<Course, Long> {


}
