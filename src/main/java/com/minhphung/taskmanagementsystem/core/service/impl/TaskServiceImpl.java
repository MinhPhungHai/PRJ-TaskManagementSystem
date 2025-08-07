package com.minhphung.taskmanagementsystem.core.service.impl;

import com.minhphung.taskmanagementsystem.core.dto.TaskDto;
import com.minhphung.taskmanagementsystem.core.entity.Task;
import com.minhphung.taskmanagementsystem.core.exception.ResourceNotFoundException;
import com.minhphung.taskmanagementsystem.core.mapper.TaskMapper;
import com.minhphung.taskmanagementsystem.core.repository.TaskRepository;
import com.minhphung.taskmanagementsystem.core.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto getTaskById(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllTasksByUserId(Long userId) {
        List<Task> tasks = taskRepository.findAllByUserId(userId);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllCompletedTasks() {
        List<Task> tasks = taskRepository.findAllByCompleted(true);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllIncompleteTasks() {
        List<Task> tasks = taskRepository.findAllByCompleted(false);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllUrgentTasks() {
        List<Task> tasks = taskRepository.findAllByUrgent(true);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllNonUrgentTasks() {
        List<Task> tasks = taskRepository.findAllByUrgent(false);
        return tasks.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(UUID taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setUrgent(taskDto.getUrgent());
        task.setDeadline(taskDto.getDeadline());
        task.setCompleted(taskDto.getCompleted());

        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto markAsCompleted(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setCompleted(true);
        task.setDoneAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        taskRepository.deleteById(taskId);
    }
}