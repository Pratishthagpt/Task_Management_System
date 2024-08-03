package com.assignment.TaskManagementSystem.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String s) {
        super(s);
    }
}
