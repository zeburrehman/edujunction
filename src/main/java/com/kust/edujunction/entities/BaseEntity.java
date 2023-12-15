package com.kust.edujunction.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
}
