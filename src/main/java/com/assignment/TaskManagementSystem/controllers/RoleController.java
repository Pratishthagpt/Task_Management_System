package com.assignment.TaskManagementSystem.controllers;

import com.assignment.TaskManagementSystem.dtos.RoleDto;
import com.assignment.TaskManagementSystem.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createNewRole (@RequestBody RoleDto roleRequest) {

        RoleDto roleResponse = roleService.createNewRole(roleRequest.getRole());

        return new ResponseEntity<>(roleResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles () {

        List<RoleDto> roleResponse = roleService.getAllRoles();

        return new ResponseEntity<>(roleResponse, HttpStatus.OK);
    }
}
