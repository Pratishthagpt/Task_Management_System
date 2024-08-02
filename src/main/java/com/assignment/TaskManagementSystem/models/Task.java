package com.assignment.TaskManagementSystem.models;

import java.util.Date;

public class Task extends BaseModel{

    private String title;
    private String description;
    private TaskStatus status;
    private Date dueDate;
    private Date createdAt;
    private Date updatedAt;
    private User user;
}
