package com.assignment.TaskManagementSystem.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserRole extends BaseModel{

    private String role;
}
