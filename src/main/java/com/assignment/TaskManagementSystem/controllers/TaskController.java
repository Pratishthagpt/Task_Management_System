package com.assignment.TaskManagementSystem.controllers;

import com.assignment.TaskManagementSystem.dtos.DueDateRequestDto;
import com.assignment.TaskManagementSystem.dtos.SuccessResponseDto;
import com.assignment.TaskManagementSystem.dtos.TaskDTO;
import com.assignment.TaskManagementSystem.services.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByStatus (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @PathVariable ("status") String status) {

        List<TaskDTO> tasks = taskService.getAllTasksByStatus(token, status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByPriority (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @PathVariable ("priority") String priority) {

        List<TaskDTO> tasks = taskService.getAllTasksByPriority(token, priority);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/due_date")
    public ResponseEntity<List<TaskDTO>> getAllTasksByDueDate (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @RequestBody DueDateRequestDto requestDto) {

        List<TaskDTO> tasks = taskService.getAllTasksBeforeDueDate(token, requestDto);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByTitle (
            @RequestHeader (HttpHeaders.PROXY_AUTHORIZATION) String token,
            @PathVariable ("title") String title) {

        List<TaskDTO> tasks = taskService.getAllTasksByTitle(token, title);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
