package com.assignment.TaskManagementSystem.models;


import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel{

    private String token;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private SessionStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryAt;

}
