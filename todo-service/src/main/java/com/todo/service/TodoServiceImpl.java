package com.todo.service;

import com.todo.dao.TodoItemEntity;
import com.todo.dao.TodoItemRepository;
import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    @Transactional
    public TodoItemResponseDTO addTodoItem(TodoItemDTO todoItemDTO) {
        TodoItemEntity todoItemEntity = new TodoItemEntity();
        todoItemEntity.setDescription(todoItemDTO.getDescription());
        todoItemEntity.setStatus(todoItemDTO.getStatus());
        todoItemEntity.setCreationDateTime(todoItemDTO.getCreationDateTime());
        todoItemEntity.setDueDateTime(todoItemDTO.getDueDateTime());
        todoItemEntity.setDoneDateTime(todoItemDTO.getDoneDateTime());

        TodoItemEntity savedEntity = todoItemRepository.save(todoItemEntity);
        return mapEntityToResponseDTO(savedEntity);
    }

    @Override
    @Transactional
    public TodoItemResponseDTO changeTodoDescription(Long itemId, String newDescription) {
        TodoItemEntity todoItemEntity = getTodoItemEntity(itemId);
        if (todoItemEntity != null && !isPastDue(todoItemEntity)) {
            todoItemEntity.setDescription(newDescription);
            return mapEntityToResponseDTO(todoItemEntity);
        }
        return null; // Item not found or past due
    }

    @Override
    @Transactional
    public TodoItemResponseDTO markTodoAsDone(Long itemId) {
        TodoItemEntity todoItemEntity = getTodoItemEntity(itemId);
        if (todoItemEntity != null && !isPastDue(todoItemEntity)) {
            return updateStatus(itemId, "done");
        }
        return null; // Item not found or past due
    }

    @Override
    @Transactional
    public TodoItemResponseDTO markTodoAsNotDone(Long itemId) {
        TodoItemEntity todoItemEntity = getTodoItemEntity(itemId);
        if (todoItemEntity != null && !isPastDue(todoItemEntity)) {
            return updateStatus(itemId, "not done");
        }
        return null; // Item not found or past due
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItemResponseDTO> getNotDoneItems(boolean retrieveAllTodoItems) {
        List<TodoItemEntity> todoItems;
        if (retrieveAllTodoItems) {
            todoItems = todoItemRepository.findAll();
        } else {
            todoItems = todoItemRepository.findByStatus("not done");
        }

        return todoItems.stream()
                .map(this::mapEntityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TodoItemResponseDTO getTodoItemDetails(Long itemId) {
        TodoItemEntity todoItemEntity = getTodoItemEntity(itemId);
        return todoItemEntity != null ? mapEntityToResponseDTO(todoItemEntity) : null;
    }

    private TodoItemResponseDTO mapEntityToResponseDTO(TodoItemEntity entity) {
        TodoItemResponseDTO dto = new TodoItemResponseDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setCreationDateTime(entity.getCreationDateTime());
        dto.setDueDateTime(entity.getDueDateTime());
        dto.setDoneDateTime(entity.getDoneDateTime());
        return dto;
    }

    private TodoItemEntity getTodoItemEntity(Long itemId) {
        Optional<TodoItemEntity> optionalTodoItemEntity = todoItemRepository.findById(itemId);
        return optionalTodoItemEntity.orElse(null);
    }

    private TodoItemResponseDTO updateStatus(Long itemId, String newStatus) {
        TodoItemEntity todoItemEntity = getTodoItemEntity(itemId);
        if (todoItemEntity != null) {
            todoItemEntity.setStatus(newStatus);
            if ("done".equals(newStatus)) {
                todoItemEntity.setDoneDateTime(LocalDateTime.now());
            } else {
                todoItemEntity.setDoneDateTime(null);
            }

            return mapEntityToResponseDTO(todoItemEntity);
        }
        return null; // Item not found
    }

    // Helper method to check if an item is "past due"
    private boolean isPastDue(TodoItemEntity todoItemEntity) {
        return "past due".equals(todoItemEntity.getStatus());
    }
}