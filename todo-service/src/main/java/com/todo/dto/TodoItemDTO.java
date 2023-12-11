package com.todo.dto;

import java.time.LocalDateTime;

public class TodoItemDTO {
    private Long id;

    private String description;

    private String status;

    private LocalDateTime creationDateTime;

    private LocalDateTime dueDateTime;

    private LocalDateTime doneDateTime;

    public TodoItemDTO() {
    }

    public TodoItemDTO(Long id, String description, String status, LocalDateTime creationDateTime, LocalDateTime dueDateTime, LocalDateTime doneDateTime) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.creationDateTime = creationDateTime;
        this.dueDateTime = dueDateTime;
        this.doneDateTime = doneDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public LocalDateTime getDoneDateTime() {
        return doneDateTime;
    }

    public void setDoneDateTime(LocalDateTime doneDateTime) {
        this.doneDateTime = doneDateTime;
    }
}