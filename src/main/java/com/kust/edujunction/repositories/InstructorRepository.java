package com.kust.edujunction.repositories;

import com.kust.edujunction.entities.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, UUID> {
    List<InstructorEntity> findByName(String name);
}
