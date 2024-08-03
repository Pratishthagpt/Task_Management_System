package com.assignment.TaskManagementSystem.dtos;

import com.assignment.TaskManagementSystem.models.TaskStatus;
import com.assignment.TaskManagementSystem.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {

    private String id;
    private String title;
    private String description;

    private String status;

    private Date dueDate;

    private Date createdAt;

    private Date updatedAt;
    private String userId;
    private String priority;
}
