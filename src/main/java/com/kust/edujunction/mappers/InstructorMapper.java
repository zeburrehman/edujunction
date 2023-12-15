package com.kust.edujunction.mappers;

import com.kust.edujunction.dtos.InstructorDTO;
import com.kust.edujunction.entities.InstructorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    InstructorDTO entityToDTO(InstructorEntity entity);

    InstructorEntity dtoToEntity(InstructorDTO dto);
}
