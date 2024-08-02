package com.assignment.TaskManagementSystem.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRolesRequestDto {

    private List<String> roleNames;
}
