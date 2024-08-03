package com.assignment.TaskManagementSystem.services;

import com.assignment.TaskManagementSystem.dtos.TaskDTO;
import com.assignment.TaskManagementSystem.exceptions.TaskNotFoundException;
import com.assignment.TaskManagementSystem.exceptions.UnauthorizedUserAccessException;
import com.assignment.TaskManagementSystem.models.Task;
import com.assignment.TaskManagementSystem.models.TaskStatus;
import com.assignment.TaskManagementSystem.models.User;
import com.assignment.TaskManagementSystem.models.UserRole;
import com.assignment.TaskManagementSystem.repositories.TaskRepository;
import com.assignment.TaskManagementSystem.repositories.UserRepository;
import com.assignment.TaskManagementSystem.security.model.UserJwtData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private AuthService authService;
    private UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, AuthService authService, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> getAllTasks(String token) {

        UserJwtData userJwtData = authService.validateUserToken(token);

        String userId = userJwtData.getId();
        Set<UserRole> roles = userJwtData.getRoleList();

        for (UserRole role : roles) {
//            Let admin see all tasks only
            if (role.getRole().equals("ADMIN")) {
                List<Task> tasks = taskRepository.findAll();

                List<TaskDTO> taskDTOS = new ArrayList<>();

                for (Task task : tasks) {
                    TaskDTO taskDTO = convertTaskToTaskDTO(task);
                    taskDTOS.add(taskDTO);
                }

                return taskDTOS;
            }
        }
        throw new UnauthorizedUserAccessException("User does not have access to view all the tasks.");
    }

    @Override
    public TaskDTO addNewTask(String token, TaskDTO taskRequestDTO) {

//        Validating token and getting user
        UserJwtData userJwtData = authService.validateUserToken(token);

        UUID userId = UUID.fromString(userJwtData.getId());

        User user = userRepository.findById(userId).get();

        Task task = new Task();

        task.setDescription(taskRequestDTO.getDescription());
        task.setTitle(taskRequestDTO.getTitle());
        task.setStatus(TaskStatus.Todo);
        task.setPriority(taskRequestDTO.getPriority());
        task.setDueDate(taskRequestDTO.getDueDate());
        task.setUser(user);
        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());

        Task savedTask = taskRepository.save(task);

        TaskDTO taskDto = convertTaskToTaskDTO(savedTask);

        return taskDto;
    }

    @Override
    public TaskDTO getTaskById(String token, String taskId) {

        UserJwtData userJwtData = authService.validateUserToken(token);

        String userId = userJwtData.getId();
        Set<UserRole> roles = userJwtData.getRoleList();

        for (UserRole role : roles) {
//            Let admin see any task by its id only
            if (role.getRole().equals("ADMIN")) {
                Optional<Task> taskOptional = taskRepository.findById(UUID.fromString(taskId));

                if (taskOptional.isEmpty()) {
                    throw new TaskNotFoundException("Task with id - " + taskId + " does not exist.");
                }

                Task task = taskOptional.get();
                TaskDTO taskDTO = convertTaskToTaskDTO(task);

                return taskDTO;
            }
        }
        throw new UnauthorizedUserAccessException("User does not have access to view this task.");
    }

    @Override
    public List<TaskDTO> getAllTasksByUser(String token) {
//        Validating token and getting user id from token
        UserJwtData userJwtData = authService.validateUserToken(token);

        UUID userId = UUID.fromString(userJwtData.getId());

//        finding user from repository based on id
        User user = userRepository.findById(userId).get();

        List<Task> tasks = taskRepository.findAllByUser(user);

//        converting task to taskDto
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task task : tasks) {
            TaskDTO taskDTO = convertTaskToTaskDTO(task);
            taskDTOS.add(taskDTO);
        }

        return taskDTOS;
    }

    @Override
    public TaskDTO updateTaskById(String token, String taskId, TaskDTO taskUpdateDto) {
//        Validating token and getting user id from token
        UserJwtData userJwtData = authService.validateUserToken(token);
        UUID userId = UUID.fromString(userJwtData.getId());

//        finding user from repository based on id
        User user = userRepository.findById(userId).get();

//        getting task by id
        Optional<Task> taskOptional = taskRepository.findById(UUID.fromString(taskId));

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException("Task with id - " + taskId + " does not exist.");
        }

        Task task = taskOptional.get();

//        setting up updated values of task
        task.setDescription(taskUpdateDto.getDescription());
        task.setTitle(taskUpdateDto.getTitle());
        task.setStatus(TaskStatus.valueOf(taskUpdateDto.getStatus()));
        task.setPriority(taskUpdateDto.getPriority());
        task.setDueDate(taskUpdateDto.getDueDate());
        task.setUser(user);
        task.setUpdatedAt(new Date());

        Task savedTask = taskRepository.save(task);

        TaskDTO taskDto = convertTaskToTaskDTO(savedTask);

        return taskDto;
    }

    @Override
    public void deleteTaskById(String token, String taskId) {
//        Validating token for user
        UserJwtData userJwtData = authService.validateUserToken(token);

        UUID userId = UUID.fromString(userJwtData.getId());

//        finding user from repository based on id
        User user = userRepository.findById(userId).get();

        List<Task> tasks = taskRepository.findAllByUser(user);

        for (Task task : tasks) {
            if (task.getId().toString().equals(taskId)) {
//                setting user as null
                task.setUser(null);
//                deleting task
                taskRepository.deleteById(UUID.fromString(taskId));
//                removing task from list of tasks
                tasks.remove(task);
                break;
            }
        }
    }

    private TaskDTO convertTaskToTaskDTO (Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(String.valueOf(task.getId()));
        taskDTO.setDescription(task.getDescription());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setStatus(String.valueOf(task.getStatus()));
        taskDTO.setUserId(String.valueOf(task.getUser().getId()));
        taskDTO.setUpdatedAt(task.getUpdatedAt());
        taskDTO.setPriority(task.getPriority());

        return taskDTO;
    }
}
