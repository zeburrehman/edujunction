package com.kust.edujunction.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class UserRoleEntity extends BaseEntity {
    private String name;
}
