package com.assignment.TaskManagementSystem.exceptions;

public class SessionHasExpiredException extends RuntimeException {
    public SessionHasExpiredException(String s) {
        super(s);
    }
}
