package com.minhphung.taskmanagementsystem.core.controller;

import com.minhphung.taskmanagementsystem.core.dto.TaskDto;
import com.minhphung.taskmanagementsystem.core.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    //Add a task REST Api
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto savedTaskDto = taskService.createTask(taskDto);
        return new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
    }

    //Search task by ID REST Api
    @GetMapping("/id/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") UUID taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDto);
    }

    //View all tasks REST Api
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TaskDto>> getAllTasksByUserId(@PathVariable("id") Long employeeId){
        List<TaskDto> tasks = taskService.getAllTasksByUserId(employeeId);
        return ResponseEntity.ok(tasks);
    }

    //View all completed task REST Api
    @GetMapping("/completed")
    public ResponseEntity<List<TaskDto>> getAllCompletedTasks(){
        List<TaskDto> tasks = taskService.getAllCompletedTasks();
        return ResponseEntity.ok(tasks);
    }

    //View all incomplete task REST Api
    @GetMapping("/incomplete")
    public ResponseEntity<List<TaskDto>> getAllIncompleteTasks(){
        List<TaskDto> tasks = taskService.getAllIncompleteTasks();
        return ResponseEntity.ok(tasks);
    }

    //View all urgent task REST Api
    @GetMapping("/urgent")
    public ResponseEntity<List<TaskDto>> getAllUrgentTasks(){
        List<TaskDto> tasks = taskService.getAllUrgentTasks();
        return ResponseEntity.ok(tasks);
    }

    //View all non-urgent task REST Api
    @GetMapping("/nonurgent")
    public ResponseEntity<List<TaskDto>> getAllNonUrgentTasks(){
        List<TaskDto> tasks = taskService.getAllNonUrgentTasks();
        return ResponseEntity.ok(tasks);
    }

    //Update task by ID REST Api
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") UUID taskId, @RequestBody TaskDto taskDto){
        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updatedTaskDto);
    }

    //Update: mark task as done by ID REST Api
    @PutMapping("/completed/{id}")
    public ResponseEntity<TaskDto> markAsCompleted(@PathVariable("id") UUID taskId){
        TaskDto updatedTaskDto = taskService.markAsCompleted(taskId);
        return ResponseEntity.ok(updatedTaskDto);
    }

    //Delete task by ID REST Api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") UUID taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Employee with ID: " + taskId + " has been deleted!");
    }
}
