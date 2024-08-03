package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    public List<TaskDTO> getAllTasks(String token);

    TaskDTO addNewTask(String token, TaskDTO taskRequestDTO);

    TaskDTO getTaskById(String token, String taskId);

    List<TaskDTO> getAllTasksByUser(String token);

    TaskDTO updateTaskById(String token, String taskId, TaskDTO taskUpdateDto);

    void deleteTaskById(String token, String taskId);
}
