package com.assignment.TaskManagementSystem.models;

import java.util.HashSet;
import java.util.Set;

public class User extends BaseModel{

    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();

}
