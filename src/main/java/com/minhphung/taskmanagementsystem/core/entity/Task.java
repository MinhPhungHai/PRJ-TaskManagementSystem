package com.minhphung.taskmanagementsystem.core.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "task")
public class Task {
    //ID
    @Id
    @GeneratedValue
    UUID id;

    //User input
    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "deadline", nullable = false)
    LocalDateTime deadline;

    @Column(name = "urgent")
    Boolean urgent;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "completed")
    Boolean completed;

    @Column(name = "done_at")
    LocalDateTime doneAt;

    @Column(name = "user_id")
    Long userId;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        completed = false;
        doneAt = null;
    }
}