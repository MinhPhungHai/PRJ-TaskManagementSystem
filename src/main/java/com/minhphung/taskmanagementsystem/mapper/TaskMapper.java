package com.minhphung.taskmanagementsystem.mapper;

import com.minhphung.taskmanagementsystem.dto.TaskDto;
import com.minhphung.taskmanagementsystem.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);
}