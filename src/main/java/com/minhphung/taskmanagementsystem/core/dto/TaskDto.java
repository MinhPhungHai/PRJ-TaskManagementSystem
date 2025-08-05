package com.minhphung.taskmanagementsystem.core.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {

    //ID
    UUID taskId;

    //User input
    String title;
    String description;
    LocalDateTime deadline;
    Boolean urgent;
    LocalDateTime startTime;
    LocalDateTime endTime;

    //Not a user input
    LocalDateTime createdAt;

    //Change after
    Boolean completed;
    LocalDateTime doneAt;

    //Foreign key
    Long userId;
}
