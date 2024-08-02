package com.assignment.TaskManagementSystem.controllers;

import com.assignment.TaskManagementSystem.dtos.SetUserRolesRequestDto;
import com.assignment.TaskManagementSystem.dtos.UserDto;
import com.assignment.TaskManagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserDetails (@PathVariable ("id") String userId) {

        UserDto userDto = userService.getUserDetailsById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers () {

        List<UserDto> userList = userService.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles (
            @PathVariable("id") String userId,
            @RequestBody SetUserRolesRequestDto setUserRolesRequestDto) {

        UserDto user = userService.setUserRoles(userId, setUserRolesRequestDto);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
