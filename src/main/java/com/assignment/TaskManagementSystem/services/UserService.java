package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.UserDto;
import com.assignment.TaskManagementSystem.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto getUserDetailsById(String userId);

    List<UserDto> getAllUsers();
}
