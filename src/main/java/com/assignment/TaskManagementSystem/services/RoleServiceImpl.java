package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.RoleDto;
import com.assignment.TaskManagementSystem.exceptions.RoleAlreadyPresentException;
import com.assignment.TaskManagementSystem.models.UserRole;
import com.assignment.TaskManagementSystem.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createNewRole(String roleName) {

//        check if role already present in db or not
        Optional<UserRole> roleOptional = roleRepository.findByRole(roleName);
//        if present, throw an exception
        if (roleOptional.isPresent()) {
            throw new RoleAlreadyPresentException("Role - " + roleName + " already present. Duplicate not allowed.");
        }

//        if not, then create new role
        UserRole role = new UserRole();
        role.setRole(roleName);

        UserRole savedRole = roleRepository.save(role);

        RoleDto createdRole = new RoleDto();
        createdRole.setRoleId(String.valueOf(savedRole.getId()));
        createdRole.setRole(savedRole.getRole());

        return createdRole;
    }

    @Override
    public List<RoleDto> getAllRoles() {

        List<UserRole> userRoles = roleRepository.findAll();

        List<RoleDto> roleDtoList = new ArrayList<>();

        for (UserRole role : userRoles) {
            RoleDto roleDto = new RoleDto();
            roleDto.setRoleId(String.valueOf(role.getId()));
            roleDto.setRole(role.getRole());

            roleDtoList.add(roleDto);
        }

        return roleDtoList;
    }
}
