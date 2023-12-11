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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void changeTodoDescriptionTest() {
        // Arrange
        Long itemId = 1L;
        String newDescription = "New Description";
        TodoItemEntity existingTodo = new TodoItemEntity(itemId, "Task 1", "not done", LocalDateTime.now(), null, null);
        TodoItemResponseDTO expectedResponse = new TodoItemResponseDTO(itemId, newDescription, "not done", LocalDateTime.now(), null, null);

        // For the non-existent item case
        when(todoItemRepository.findById(itemId)).thenReturn(Optional.empty());

        // For the existing item case
        when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodo));
        when(todoItemRepository.save(any())).thenReturn(new TodoItemEntity(itemId, newDescription, "not done", LocalDateTime.now(), null, null));

        // Act and Assert for non-existent item case
        TodoItemResponseDTO nonExistentResult = todoService.changeTodoDescription(2L, newDescription);
        assertNull(nonExistentResult);

        // Act and Assert for existing item case
        TodoItemResponseDTO result = todoService.changeTodoDescription(itemId, newDescription);
        assertNotNull(result);
        assertEquals(expectedResponse.getDescription(), result.getDescription());
    }

    @Test
    void markTodoAsDoneTest() {
        // Arrange
        Long itemId = 1L;
        TodoItemEntity existingTodo = new TodoItemEntity(itemId, "Task 1", "not done", LocalDateTime.now(), null, null);
        TodoItemResponseDTO expectedResponse = new TodoItemResponseDTO(itemId, "Task 1", "done", LocalDateTime.now(), null, null);

        when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodo));
        when(todoItemRepository.save(any())).thenReturn(new TodoItemEntity(itemId, "Task 1", "done", LocalDateTime.now(), LocalDateTime.now(), null));

        // Act
        TodoItemResponseDTO result = todoService.markTodoAsDone(itemId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getDescription(), result.getDescription());
        assertEquals(expectedResponse.getStatus(), result.getStatus());
        assertNotNull(result.getDoneDateTime());
    }

    @Test
    void markTodoAsNotDoneTest() {
        // Arrange
        Long itemId = 1L;
        TodoItemEntity existingTodo = new TodoItemEntity(itemId, "Task 1", "done", LocalDateTime.now(), LocalDateTime.now(), null);
        TodoItemResponseDTO expectedResponse = new TodoItemResponseDTO(itemId, "Task 1", "not done", LocalDateTime.now(), null, null);

        when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodo));
        when(todoItemRepository.save(any())).thenReturn(new TodoItemEntity(itemId, "Task 1", "not done", LocalDateTime.now(), null, null));

        // Act
        TodoItemResponseDTO result = todoService.markTodoAsNotDone(itemId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getDescription(), result.getDescription());
        assertEquals(expectedResponse.getStatus(), result.getStatus());
        assertNull(result.getDoneDateTime());
    }

    @Test
    void getNotDoneItemsTest() {
        // Arrange
        when(todoItemRepository.findByStatus("not done")).thenReturn(Arrays.asList(
                new TodoItemEntity(1L, "Task 1", "not done", LocalDateTime.now(), null, null),
                new TodoItemEntity(2L, "Task 2", "not done", LocalDateTime.now(), null, null)
        ));

        when(todoItemRepository.findAll()).thenReturn(Arrays.asList(
                new TodoItemEntity(1L, "Task 1", "not done", LocalDateTime.now(), null, null),
                new TodoItemEntity(2L, "Task 2", "done", LocalDateTime.now(), null, null),
                new TodoItemEntity(3L, "Task 3", "not done", LocalDateTime.now(), null, null)
        ));

        // Act
        List<TodoItemResponseDTO> resultNotDone = todoService.getNotDoneItems(false);
        List<TodoItemResponseDTO> resultAll = todoService.getNotDoneItems(true);

        // Assert
        assertNotNull(resultNotDone);
        assertEquals(2, resultNotDone.size());

        assertNotNull(resultAll);
        assertEquals(3, resultAll.size());
    }

    @Test
    void getTodoItemDetailsTest() {
        // Arrange
        Long itemId = 1L;
        LocalDateTime creationDateTime = LocalDateTime.now();
        TodoItemEntity existingTodo = new TodoItemEntity(itemId, "Task 1", "not done", creationDateTime, null, null);
        TodoItemResponseDTO expectedResponse = new TodoItemResponseDTO(itemId, "Task 1", "not done", creationDateTime, null, null);

        when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodo));

        // Act
        TodoItemResponseDTO result = todoService.getTodoItemDetails(itemId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getDescription(), result.getDescription());
        assertEquals(expectedResponse.getStatus(), result.getStatus());
        assertEquals(expectedResponse.getCreationDateTime(), result.getCreationDateTime());
        assertEquals(expectedResponse.getDueDateTime(), result.getDueDateTime());
        assertEquals(expectedResponse.getDoneDateTime(), result.getDoneDateTime());
    }
}