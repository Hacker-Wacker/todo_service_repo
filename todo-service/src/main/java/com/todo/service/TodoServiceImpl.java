package com.todo.service;

import com.todo.dao.TodoItemEntity;
import com.todo.dao.TodoItemRepository;
import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public TodoItemResponseDTO changeTodoDescription(Long itemId, String newDescription) {
        return null;
    }

    @Override
    public TodoItemResponseDTO markTodoAsDone(Long itemId) {
        return null;
    }

    @Override
    public TodoItemResponseDTO markTodoAsNotDone(Long itemId) {
        return null;
    }

    @Override
    public List<TodoItemResponseDTO> getTodoItems() {
        return null;
    }

    @Override
    public TodoItemResponseDTO getTodoItemDetails(Long itemId) {
        return null;
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
}