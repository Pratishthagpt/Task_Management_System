package com.assignment.TaskManagementSystem.security.model;

import com.assignment.TaskManagementSystem.models.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserJwtData {

    private String id;
    private String username;
    private Set<UserRole> roleList;

}
