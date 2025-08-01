package com.school.mapper;

import com.school.Entity.Student;
import com.school.dto.StudentsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentsDTO toDto(Student student);

    Student toEntity(StudentsDTO dto);

    List<StudentsDTO> toDTOList(List<Student> student);

}
