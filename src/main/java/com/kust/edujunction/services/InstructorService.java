package com.kust.edujunction.services;

import com.kust.edujunction.common.exceptions.NotFoundException;
import com.kust.edujunction.dtos.InstructorDTO;
import com.kust.edujunction.entities.InstructorEntity;
import com.kust.edujunction.mappers.InstructorMapper;
import com.kust.edujunction.repositories.InstructorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    public List<InstructorDTO> getAll() {
        return this.instructorRepository.findAll().stream().map(instructorMapper::entityToDTO).toList();
    }

    @Transactional
    public InstructorEntity save(InstructorDTO instructorDTO) {
        return this.instructorRepository.save(instructorMapper.dtoToEntity(instructorDTO));
    }

    public InstructorDTO getById(String id) {
        var optionalInstructor = this.instructorRepository.findById(UUID.fromString(id));
        if (optionalInstructor.isEmpty()) throw new NotFoundException("Instructor with id " + id + " not found");
        return instructorMapper.entityToDTO(optionalInstructor.get());
    }

    public Page<InstructorDTO> getPaginated(Integer pageNumber, Integer pageSize, String sort) {
        if (pageNumber == 0) pageNumber = 0;
        if (pageSize == 0) pageSize = 1000;

        Pageable pageable = null;
        if (!StringUtils.isEmpty(sort)) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return instructorRepository.findAll(pageable).map(instructorMapper::entityToDTO);
    }
}
