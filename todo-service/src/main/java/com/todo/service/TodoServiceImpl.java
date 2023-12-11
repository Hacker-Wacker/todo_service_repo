package com.todo.service;

import com.todo.dao.TodoItemRepository;
import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public TodoItemResponseDTO addTodoItem(TodoItemDTO todoItem) {
        return null;
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
}