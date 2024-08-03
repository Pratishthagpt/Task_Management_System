package com.assignment.TaskManagementSystem.repositories;

import com.assignment.TaskManagementSystem.models.Task;
import com.assignment.TaskManagementSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByUser(User user);
}
