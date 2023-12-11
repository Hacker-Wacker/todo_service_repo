package com.todo.service;

import com.todo.dao.TodoItemEntity;
import com.todo.dao.TodoItemRepository;
import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addTodoItemTest() {
        LocalDateTime creationDateTime = LocalDateTime.now();

        // Arrange
        TodoItemDTO todoItemDTO = new TodoItemDTO(1L, "Task 1", "not done", creationDateTime, null, null);
        TodoItemResponseDTO expectedResponse = new TodoItemResponseDTO(1L, "Task 1", "not done", creationDateTime, null, null);

        when(todoItemRepository.save(any())).thenReturn(new TodoItemEntity(1L, "Task 1", "not done", creationDateTime, null, null));

        // Act
        TodoItemResponseDTO result = todoService.addTodoItem(todoItemDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getDescription(), result.getDescription());
        assertEquals(expectedResponse.getStatus(), result.getStatus());
        assertEquals(expectedResponse.getCreationDateTime(), result.getCreationDateTime());
        assertEquals(expectedResponse.getDueDateTime(), result.getDueDateTime());
        assertEquals(expectedResponse.getDoneDateTime(), result.getDoneDateTime());
    }
}