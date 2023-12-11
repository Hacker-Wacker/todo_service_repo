package com.todo.dao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_todo_item")
public class TodoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "creation_date_time", nullable = false)
    private LocalDateTime creationDateTime;

    @Column(name = "due_date_time")
    private LocalDateTime dueDateTime;

    @Column(name = "done_date_time")
    private LocalDateTime doneDateTime;

    public Long getId() {
        return id;
    }

    public TodoItemEntity() {}

    public TodoItemEntity(Long id, String description, String status, LocalDateTime creationDateTime, LocalDateTime dueDateTime, LocalDateTime doneDateTime) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.creationDateTime = creationDateTime;
        this.dueDateTime = dueDateTime;
        this.doneDateTime = doneDateTime;
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