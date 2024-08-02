package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.UserDto;
import com.assignment.TaskManagementSystem.exceptions.UserNotFoundException;
import com.assignment.TaskManagementSystem.models.User;
import com.assignment.TaskManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
