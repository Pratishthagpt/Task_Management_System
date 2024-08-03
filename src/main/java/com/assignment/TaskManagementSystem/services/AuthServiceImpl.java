package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.LoginRequestDto;
import com.assignment.TaskManagementSystem.dtos.LogoutRequestDto;
import com.assignment.TaskManagementSystem.dtos.SignUpRequestDto;
import com.assignment.TaskManagementSystem.dtos.UserDto;
import com.assignment.TaskManagementSystem.exceptions.InvalidPasswordException;
import com.assignment.TaskManagementSystem.exceptions.SessionNotFoundException;
import com.assignment.TaskManagementSystem.exceptions.UserNotFoundException;
import com.assignment.TaskManagementSystem.models.Session;
import com.assignment.TaskManagementSystem.models.SessionStatus;
import com.assignment.TaskManagementSystem.models.User;
import com.assignment.TaskManagementSystem.models.UserRole;
import com.assignment.TaskManagementSystem.repositories.SessionRepository;
import com.assignment.TaskManagementSystem.repositories.UserRepository;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.interfaces.RSAKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private SecretKey secretKey;
    private SessionRepository sessionRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           SessionRepository sessionRepository
                           ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRepository = sessionRepository;

        this.secretKey = Jwts.SIG.HS512.key().build();
    }

    @Override
    public UserDto signUpUser(SignUpRequestDto signUpRequestDto) {

        User user = new User();

        user.setUsername(signUpRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

        User savedUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setUserId(String.valueOf(savedUser.getId()));
        userDto.setUsername(savedUser.getUsername());
        userDto.setRoles(new HashSet<>(savedUser.getRoles()));

        return userDto;
    }

    @Override
    public User loginUser(LoginRequestDto loginRequestDto) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

//        first checking whether user exists with username or not
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User is not registered. Please register first for login.");
        }

        User user = userOptional.get();

//        checking for the correct password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Password does not matches. Please enter the correct password.");
        }

        return user;
    }

    @Override
    public String generateJwtToken(User user) {

//        using map data structure for storing the jwt data as key-value pair
        Map<String, Object> jwtData = new HashMap<>();

        LocalDate expiryDate = LocalDate.now().plusDays(15);
        Date expiryDateUtil = java.sql.Date.valueOf(expiryDate);

//        setting up role
        List<String> roles = new ArrayList<>();
        for (UserRole role : user.getRoles()) {
            roles.add(role.getRole());
        }

        jwtData.put("userId", user.getId().toString());
        jwtData.put("username", user.getUsername());
        jwtData.put("roles", roles);
        jwtData.put("createdAt", new Date());
        jwtData.put("expiryAt", expiryDateUtil);

        String token = Jwts
                .builder()
                .claims(jwtData)
                .signWith(secretKey)
                .compact();

        return token;
    }

    @Override
    public Session createSession(User user, String token) {

        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setStatus(SessionStatus.ACTIVE);

        LocalDate expiryDate = LocalDate.now().plusDays(15);
        Date expiryDateUtil = java.sql.Date.valueOf(expiryDate);

        session.setExpiryAt(expiryDateUtil);
        Session savedSession = sessionRepository.save(session);

        return savedSession;
    }

    @Override
    public void logoutUser(LogoutRequestDto logoutRequestDto) {

//        while logout, putting session on soft delete, only changing the status but not deleting it from db

//        first get the user by userId
        Optional<User> userOptional = userRepository.findById(UUID.fromString(logoutRequestDto.getUserId()));
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }
        User user = userOptional.get();

//        get token from dto
        String token = logoutRequestDto.getToken();

//        getting session based on token and user
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser(token, user);

        if (sessionOptional.isEmpty()) {
            throw new SessionNotFoundException("Session not found, please login again.");
        }

        Session session = sessionOptional.get();
//        setting up the session status to ENDED for logout user
        session.setStatus(SessionStatus.ENDED);

        sessionRepository.save(session);
    }
}
