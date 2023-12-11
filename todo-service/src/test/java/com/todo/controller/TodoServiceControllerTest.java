package com.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.dto.TodoItemDTO;
import com.todo.dto.TodoItemResponseDTO;
import com.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoServiceControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoServiceController todoServiceController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoServiceController).build();
    }

    @Test
    public void addTodoItemTest() throws Exception {
        // Test with a valid status
        TodoItemDTO validTodoItemDTO = new TodoItemDTO();
        validTodoItemDTO.setDescription("Test Item");
        validTodoItemDTO.setStatus("not done");

        TodoItemResponseDTO responseDTO = new TodoItemResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setDescription("Test Item");
        responseDTO.setStatus("not done");

        when(todoService.addTodoItem(any(TodoItemDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/todoItems/add")
                        .content(objectMapper.writeValueAsString(validTodoItemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        // Test with an invalid status
        TodoItemDTO invalidTodoItemDTO = new TodoItemDTO();
        invalidTodoItemDTO.setDescription("Test Item");
        invalidTodoItemDTO.setStatus("invalid_status");

        mockMvc.perform(post("/api/todoItems/add")
                        .content(objectMapper.writeValueAsString(invalidTodoItemDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); // Expecting a Bad Request status
    }

    @Test
    public void changeTodoDescriptionTest() throws Exception {
        Long itemId = 1L;
        String newDescription = "New Description";

        TodoItemResponseDTO responseDTO = new TodoItemResponseDTO();
        responseDTO.setId(itemId);
        responseDTO.setDescription(newDescription);
        responseDTO.setStatus("not done");

        when(todoService.changeTodoDescription(eq(itemId), eq(newDescription))).thenReturn(responseDTO);

        ResultActions resultActions = mockMvc.perform(put("/api/todoItems/changeDescription/{itemId}", itemId)
                        .param("newDescription", newDescription))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(newDescription));
    }

    @Test
    public void markTodoAsDoneTest() throws Exception {
        Long itemId = 1L;

        TodoItemResponseDTO responseDTO = new TodoItemResponseDTO();
        responseDTO.setId(itemId);
        responseDTO.setDescription("Test Item");
        responseDTO.setStatus("done");

        when(todoService.markTodoAsDone(eq(itemId))).thenReturn(responseDTO);

        ResultActions resultActions = mockMvc.perform(put("/api/todoItems/markAsDone/{itemId}", itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("done"));
    }

    @Test
    public void markTodoAsNotDoneTest() throws Exception {
        Long itemId = 1L;

        TodoItemResponseDTO responseDTO = new TodoItemResponseDTO();
        responseDTO.setId(itemId);
        responseDTO.setDescription("Test Item");
        responseDTO.setStatus("not done");

        when(todoService.markTodoAsNotDone(eq(itemId))).thenReturn(responseDTO);

        ResultActions resultActions = mockMvc.perform(put("/api/todoItems/markAsNotDone/{itemId}", itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("not done"));
    }

    @Test
    public void getTodoItemsTest() throws Exception {
        TodoItemResponseDTO item1 = new TodoItemResponseDTO();
        item1.setId(1L);
        item1.setDescription("Item 1");
        item1.setStatus("not done");

        TodoItemResponseDTO item2 = new TodoItemResponseDTO();
        item2.setId(2L);
        item2.setDescription("Item 2");
        item2.setStatus("not done");

        List<TodoItemResponseDTO> responseDTOList = List.of(item1, item2);

        when(todoService.getTodoItems()).thenReturn(responseDTOList);

        ResultActions resultActions = mockMvc.perform(get("/api/todoItems/notDoneItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void getTodoItemDetailsTest() throws Exception {
        Long itemId = 1L;

        TodoItemResponseDTO responseDTO = new TodoItemResponseDTO();
        responseDTO.setId(itemId);
        responseDTO.setDescription("Test Item");
        responseDTO.setStatus("not done");

        when(todoService.getTodoItemDetails(eq(itemId))).thenReturn(responseDTO);

        ResultActions resultActions = mockMvc.perform(get("/api/todoItems/{itemId}", itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
