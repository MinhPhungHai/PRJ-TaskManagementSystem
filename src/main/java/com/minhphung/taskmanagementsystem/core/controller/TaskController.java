package com.minhphung.taskmanagementsystem.core.controller;

import com.minhphung.taskmanagementsystem.core.dto.TaskDto;
import com.minhphung.taskmanagementsystem.core.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    //Add a task REST Api
    @PostMapping("/create")
    @Secured("CREATE_TASK")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto savedTaskDto = taskService.createTask(taskDto);
        return new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
    }

    //Search task by ID REST Api
    @GetMapping("/search/id/{task_id}")
    @Secured("GET_TASK")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("task_id") UUID taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDto);
    }

    //View all tasks REST Api
    @GetMapping("/search/all")
    @Secured("GET_ALL_TASKS")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/user/{user_id}")
    @Secured("GET_ALL_TASKS")
    public ResponseEntity<List<TaskDto>> getAllTasksByUserId(@PathVariable("user_id") Long employeeId){
        List<TaskDto> tasks = taskService.getAllTasksByUserId(employeeId);
        return ResponseEntity.ok(tasks);
    }

    //View all completed task REST Api
    @GetMapping("/search/completed")
    @Secured("GET_ALL_TASKS")
    public ResponseEntity<List<TaskDto>> getAllCompletedTasks(){
        List<TaskDto> tasks = taskService.getAllCompletedTasks();
        return ResponseEntity.ok(tasks);
    }

    //View all incomplete task REST Api
    @GetMapping("/search/incomplete")
    @Secured("GET_ALL_TASKS")
    public ResponseEntity<List<TaskDto>> getAllIncompleteTasks(){
        List<TaskDto> tasks = taskService.getAllIncompleteTasks();
        return ResponseEntity.ok(tasks);
    }

    //View all urgent task REST Api
    @GetMapping("/search/urgent")
    @Secured("GET_ALL_TASKS")
    public ResponseEntity<List<TaskDto>> getAllUrgentTasks(){
        List<TaskDto> tasks = taskService.getAllUrgentTasks();
        return ResponseEntity.ok(tasks);
    }

    //View all non-urgent task REST Api
    @GetMapping("/search/nonurgent")
    @Secured("GET_ALL_TASKS")
    public ResponseEntity<List<TaskDto>> getAllNonUrgentTasks(){
        List<TaskDto> tasks = taskService.getAllNonUrgentTasks();
        return ResponseEntity.ok(tasks);
    }

    //Update task by ID REST Api
    @PutMapping("/update/{task_id}")
    @Secured("UPDATE_TASK")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("task_id") UUID taskId, @RequestBody TaskDto taskDto){
        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updatedTaskDto);
    }

    //Update: mark task as done by ID REST Api
    @PutMapping("/completed/{task_id}")
    @Secured("MARK_COMPLETED")
    public ResponseEntity<TaskDto> markAsCompleted(@PathVariable("task_id") UUID taskId){
        TaskDto updatedTaskDto = taskService.markAsCompleted(taskId);
        return ResponseEntity.ok(updatedTaskDto);
    }

    //Delete task by ID REST Api
    @DeleteMapping("/delete/{task_id}")
    @Secured("DELETE_TASK")
    public ResponseEntity<String> deleteTask(@PathVariable("task_id") UUID taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task with ID: " + taskId + " has been deleted!");
    }
}