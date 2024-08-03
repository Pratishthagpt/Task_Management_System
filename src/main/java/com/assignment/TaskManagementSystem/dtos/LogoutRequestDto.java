package com.assignment.TaskManagementSystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {

    private String userId;
    private String token;
}
