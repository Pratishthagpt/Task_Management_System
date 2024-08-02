package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.SetUserRolesRequestDto;
import com.assignment.TaskManagementSystem.dtos.UserDto;
import com.assignment.TaskManagementSystem.exceptions.RoleNotFoundException;
import com.assignment.TaskManagementSystem.exceptions.UserNotFoundException;
import com.assignment.TaskManagementSystem.models.User;
import com.assignment.TaskManagementSystem.models.UserRole;
import com.assignment.TaskManagementSystem.repositories.RoleRepository;
import com.assignment.TaskManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto getUserDetailsById(String userId) {

        UUID uuid = UUID.fromString(userId);

        Optional<User> userOptional = userRepository.findById(uuid);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id - " + userId + " not found.");
        }

        User user = userOptional.get();

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUsername(user.getUsername());
        userDto.setRoles(new HashSet<>(user.getRoles()));

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setUserId(String.valueOf(user.getId()));
            userDto.setUsername(user.getUsername());
            userDto.setRoles(new HashSet<>(user.getRoles()));

            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Override
    public UserDto setUserRoles(String userId, SetUserRolesRequestDto setUserRolesRequestDto) {

//        check if user id present by id or not
        UUID uuid = UUID.fromString(userId);

        Optional<User> userOptional = userRepository.findById(uuid);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id - " + userId + " not found.");
        }

        User user = userOptional.get();

        Set<UserRole> userRoles = user.getRoles();

//        setting user roles from request
        for (String roleName : setUserRolesRequestDto.getRoleNames()) {
            Optional<UserRole> roleOptional = roleRepository.findByRole(roleName);
            if (roleOptional.isEmpty()) {
                throw new RoleNotFoundException("Given role is not present.");
            }
            UserRole role = roleOptional.get();

            userRoles.add(role);
        }

        user.setRoles(userRoles);
        User savedUser = userRepository.save(user);

//        setting up userDto from user
        UserDto userDto = new UserDto();
        userDto.setUserId(String.valueOf(savedUser.getId()));
        userDto.setUsername(savedUser.getUsername());
        userDto.setRoles(new HashSet<>(savedUser.getRoles()));

        return userDto;
    }
}
