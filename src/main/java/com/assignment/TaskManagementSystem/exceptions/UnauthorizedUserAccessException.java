package com.assignment.TaskManagementSystem.exceptions;

public class UnauthorizedUserAccessException extends RuntimeException {
    public UnauthorizedUserAccessException(String s) {
        super(s);
    }
}
