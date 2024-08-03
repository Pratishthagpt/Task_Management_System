package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.LoginRequestDto;
import com.assignment.TaskManagementSystem.dtos.LogoutRequestDto;
import com.assignment.TaskManagementSystem.dtos.SignUpRequestDto;
import com.assignment.TaskManagementSystem.dtos.UserDto;
import com.assignment.TaskManagementSystem.models.Session;
import com.assignment.TaskManagementSystem.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDto signUpUser(SignUpRequestDto signUpRequestDto);

    User loginUser(LoginRequestDto loginRequestDto);

    String generateJwtToken(User user);

    Session createSession(User user, String token);

    void logoutUser(LogoutRequestDto logoutRequestDto);
}
