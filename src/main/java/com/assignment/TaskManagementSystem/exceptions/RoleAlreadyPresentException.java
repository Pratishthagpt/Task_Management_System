package com.assignment.TaskManagementSystem.exceptions;

public class RoleAlreadyPresentException extends RuntimeException {
    public RoleAlreadyPresentException(String s) {
        super(s);
    }
}
