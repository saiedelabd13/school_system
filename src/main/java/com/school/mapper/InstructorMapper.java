package com.school.mapper;

import com.school.Entity.Instructor;
import com.school.dto.InstructorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    InstructorDTO toDto(Instructor instructor);

    Instructor toEntity(InstructorDTO dto);

    List<InstructorDTO> toDTOList(List<Instructor> instructors);
}
