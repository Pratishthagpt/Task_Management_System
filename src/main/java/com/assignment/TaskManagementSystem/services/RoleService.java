package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    RoleDto createNewRole(String roleName);

    List<RoleDto> getAllRoles();
}
