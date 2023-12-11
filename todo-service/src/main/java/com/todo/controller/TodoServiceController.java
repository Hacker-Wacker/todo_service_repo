package com.todo.controller;

import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;
import com.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todoItems")
public class TodoServiceController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public TodoItemResponseDTO addTodoItem(@Valid @RequestBody TodoItemDTO todoItemDTO) {
        return todoService.addTodoItem(todoItemDTO);
    }

    @PutMapping("/changeDescription/{itemId}")
    public TodoItemResponseDTO changeTodoDescription(
            @PathVariable Long itemId,
            @RequestParam String newDescription
    ) {
        return todoService.changeTodoDescription(itemId, newDescription);
    }

    @PutMapping("/markAsDone/{itemId}")
    public TodoItemResponseDTO markTodoAsDone(@PathVariable Long itemId) {
        return todoService.markTodoAsDone(itemId);
    }

    @PutMapping("/markAsNotDone/{itemId}")
    public TodoItemResponseDTO markTodoAsNotDone(@PathVariable Long itemId) {
        return todoService.markTodoAsNotDone(itemId);
    }

    @GetMapping("/notDoneItems")
    public List<TodoItemResponseDTO> getNotDoneItems(@RequestParam(required = false, defaultValue = "false") boolean retrieveAllTodoItems) {
        return todoService.getNotDoneItems(retrieveAllTodoItems);
    }

    @GetMapping("/{itemId}")
    public TodoItemResponseDTO getTodoItemDetails(@PathVariable Long itemId) {
        return todoService.getTodoItemDetails(itemId);
    }
}
