package com.assignment.TaskManagementSystem.controllers;

import com.assignment.TaskManagementSystem.dtos.SuccessResponseDto;
import com.assignment.TaskManagementSystem.dtos.TaskDTO;
import com.assignment.TaskManagementSystem.services.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token
    ) {
        List<TaskDTO> tasks = taskService.getAllTasks(token);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<TaskDTO> addNewTasks (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @RequestBody TaskDTO taskRequestDTO
    ) {
        TaskDTO addedTask = taskService.addNewTask(token, taskRequestDTO);

        return new ResponseEntity<>(addedTask, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> getTaskById (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @PathVariable("id") String taskId
    ) {
        TaskDTO task = taskService.getTaskById(token, taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<TaskDTO>> getAllTasksByUser (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token
    ) {
        List<TaskDTO> tasks = taskService.getAllTasksByUser(token);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTaskById (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @PathVariable("id") String taskId, @RequestBody TaskDTO taskUpdateDto
    ) {
        TaskDTO updatedTask = taskService.updateTaskById(token, taskId, taskUpdateDto);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDto> deleteTaskById (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @PathVariable("id") String taskId
    ) {
        taskService.deleteTaskById(token, taskId);

        SuccessResponseDto successResponseDto = new SuccessResponseDto("Task has been successfully deleted.", HttpStatus.OK);

        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }
}
