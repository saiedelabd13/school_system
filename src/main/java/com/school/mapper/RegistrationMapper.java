package com.school.mapper;

import com.school.Entity.Registration;
import com.school.dto.RegistrationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    RegistrationDTO toDto(Registration registration);

    Registration toEntity(RegistrationDTO dto);

    List<RegistrationDTO> toDTOList(List<Registration> registration);


}
