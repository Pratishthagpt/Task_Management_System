package com.assignment.TaskManagementSystem.exceptions;

import com.assignment.TaskManagementSystem.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, RoleNotFoundException.class})
    public ResponseEntity<ExceptionDto> handleNotFoundException(Exception exception) {

        String message;
        HttpStatus status;

        if (exception instanceof UserNotFoundException) {
            UserNotFoundException userNotFoundException = (UserNotFoundException) exception;
            message = userNotFoundException.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else if (exception instanceof RoleNotFoundException) {
            RoleNotFoundException roleNotFoundException = (RoleNotFoundException) exception;
            message = roleNotFoundException.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else {
            message = "An unexpected error occurred.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new ExceptionDto(message, status), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RoleAlreadyPresentException.class})
    public ResponseEntity<ExceptionDto> handlePresentException(Exception exception) {

        String message;
        HttpStatus status;

        if (exception instanceof RoleAlreadyPresentException) {
            RoleAlreadyPresentException roleAlreadyPresentException = (RoleAlreadyPresentException) exception;
            message = roleAlreadyPresentException.getMessage();
            status = HttpStatus.ALREADY_REPORTED;
        }
        else {
            message = "An unexpected error occurred.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new ExceptionDto(message, status), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler({InvalidPasswordException.class})
    public ResponseEntity<ExceptionDto> handleInvalidEntryException (Exception e) {
        String message;
        HttpStatus status;

        if (e instanceof InvalidPasswordException) {
            InvalidPasswordException invalidPasswordException = (InvalidPasswordException) e;
            message = invalidPasswordException.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        else {
            message = "An unexpected error occurred.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new ExceptionDto(message, status), HttpStatus.BAD_REQUEST);
    }

}
