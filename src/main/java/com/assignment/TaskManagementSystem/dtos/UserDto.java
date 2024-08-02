package com.assignment.TaskManagementSystem.dtos;

import com.assignment.TaskManagementSystem.models.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private String userId;
    private String username;

    private Set<UserRole> roles = new HashSet<>();
}
