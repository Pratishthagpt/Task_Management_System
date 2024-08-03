package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.DueDateRequestDto;
import com.assignment.TaskManagementSystem.dtos.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TaskService {

    public List<TaskDTO> getAllTasks(String token);

    TaskDTO addNewTask(String token, TaskDTO taskRequestDTO);

    TaskDTO getTaskById(String token, String taskId);

    List<TaskDTO> getAllTasksByUser(String token);

    TaskDTO updateTaskById(String token, String taskId, TaskDTO taskUpdateDto);

    void deleteTaskById(String token, String taskId);

    List<TaskDTO> getAllTasksByStatus(String token, String status);

    List<TaskDTO> getAllTasksByPriority(String token, String priority);

    List<TaskDTO> getAllTasksBeforeDueDate(String token, DueDateRequestDto requestDto);

    List<TaskDTO> getAllTasksByTitle(String token, String title);
}
