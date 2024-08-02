package com.assignment.TaskManagementSystem.models;


import java.util.Date;

public class Session extends BaseModel{

    private String token;
    private User user;
    private SessionStatus status;
    private Date expiryAt;

}
