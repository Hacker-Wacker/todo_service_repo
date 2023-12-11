package com.todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItemEntity, Long> {
    List<TodoItemEntity> findByStatus(String status);

    List<TodoItemEntity> findByDueDateTimeBeforeAndStatusNot(LocalDateTime dueDateTime, String status);
}
