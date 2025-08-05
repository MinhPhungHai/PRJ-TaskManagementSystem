package com.minhphung.taskmanagementsystem.core.service;

import com.minhphung.taskmanagementsystem.core.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    //boolean validateTaskTimeRangeOverlap(TaskDto taskDto);
    //boolean hasValidTimeRange(TaskDto taskDto);

    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(UUID taskId);
    List<TaskDto> getAllTasks();
    List<TaskDto> getAllTasksByUserId(Long employeeId);
    List<TaskDto> getAllCompletedTasks();
    List<TaskDto> getAllIncompleteTasks();
    List<TaskDto> getAllUrgentTasks();
    List<TaskDto> getAllNonUrgentTasks();

    TaskDto updateTask(UUID taskId, TaskDto taskDto);
    TaskDto markAsCompleted(UUID taskId);

    void deleteTask(UUID taskId);
}