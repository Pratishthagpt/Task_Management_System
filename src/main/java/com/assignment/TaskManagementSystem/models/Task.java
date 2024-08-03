package com.assignment.TaskManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Task extends BaseModel{

    private String title;
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private String priority;
}
