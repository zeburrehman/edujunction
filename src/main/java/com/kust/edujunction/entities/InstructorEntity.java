package com.kust.edujunction.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "instructors")
@Data
public class InstructorEntity extends BaseEntity {
    private String name;
    private String email;
}
