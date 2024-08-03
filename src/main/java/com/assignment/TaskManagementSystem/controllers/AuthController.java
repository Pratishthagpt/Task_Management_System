package com.assignment.TaskManagementSystem.controllers;

import com.assignment.TaskManagementSystem.dtos.*;
import com.assignment.TaskManagementSystem.models.Session;
import com.assignment.TaskManagementSystem.models.User;
import com.assignment.TaskManagementSystem.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> signUpUser(@RequestBody SignUpRequestDto signUpRequestDto) {

        UserDto userDto = authService.signUpUser (signUpRequestDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {

        User user = authService.loginUser (loginRequestDto);

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setUserId(String.valueOf(user.getId()));
        userDto.setRoles(user.getRoles());

//        creating JWT token
        String token = authService.generateJwtToken(user);

//        creating new session
        Session session = authService.createSession(user, token);

//        setting token in cookies in headers
        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token: " + token);

        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponseDto> logoutUser (@RequestBody LogoutRequestDto logoutRequestDto) {
        authService.logoutUser(logoutRequestDto);

        SuccessResponseDto successResponseDto = new SuccessResponseDto("User has successfully logged out.", HttpStatus.OK);

        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }
}
