package com.minhphung.taskmanagementsystem.core.mapper;

import com.minhphung.taskmanagementsystem.core.dto.TaskDto;
import com.minhphung.taskmanagementsystem.core.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);
}