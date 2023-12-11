package com.todo.service;

import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;

import java.util.List;

public interface TodoService {

    TodoItemResponseDTO addTodoItem(TodoItemDTO todoItem);

    TodoItemResponseDTO changeTodoDescription(Long itemId, String newDescription);

    TodoItemResponseDTO markTodoAsDone(Long itemId);

    TodoItemResponseDTO markTodoAsNotDone(Long itemId);

    List<TodoItemResponseDTO> getTodoItems();

    TodoItemResponseDTO getTodoItemDetails(Long itemId);
}