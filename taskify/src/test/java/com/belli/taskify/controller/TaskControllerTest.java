package com.belli.taskify.controller;

import com.belli.taskify.model.Task;
import com.belli.taskify.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void createTaskShouldReturnCreatedTask() throws Exception {
        Task saved = new Task(1L, "New Task", "Description", "Pending");
        Mockito.when(taskService.createTask(Mockito.any(Task.class))).thenReturn(saved);

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"New Task\",\"description\":\"Description\",\"status\":\"Pending\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/tasks/1")) // Verify Location header
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("New Task"))
            .andExpect(jsonPath("$.description").value("Description"))
            .andExpect(jsonPath("$.status").value("Pending"));

        // Verify that taskService.createTask was called once with a Task object having "New Task" title
        Mockito.verify(taskService, Mockito.times(1))
            .createTask(Mockito.argThat(task ->
                "New Task".equals(task.getTitle()) &&
                "Description".equals(task.getDescription()) &&
                "Pending".equals(task.getStatus())
            ));
    }

    @Test
    void getAllTasksShouldReturnList() throws Exception {
        Task t1 = new Task(1L, "Task One", "Desc One", "Pending");
        Task t2 = new Task(2L, "Task Two", "Desc Two", "Completed");

        Mockito.when(taskService.getAllTasks()).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Task One"))
            .andExpect(jsonPath("$[1].title").value("Task Two"));

        // Verify that taskService.getAllTasks was called once
        Mockito.verify(taskService, Mockito.times(1)).getAllTasks();
    }

    @Test
    void getTaskByIdShouldReturnTaskIfExists() throws Exception {
        Task t = new Task(1L, "Task One", "Desc", "Pending");
        Mockito.when(taskService.getTaskById(1L)).thenReturn(Optional.of(t));

        mockMvc.perform(get("/tasks/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Task One"));

        // Verify that taskService.getTaskById is called once with the correct id = 1
        Mockito.verify(taskService, Mockito.times(1)).getTaskById(1L);
    }

    @Test
    void getTaskByIdShouldReturnNotFoundIfDoesNotExist() throws Exception {
        Mockito.when(taskService.getTaskById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/tasks/99"))
            .andExpect(status().isNotFound());

        // Verify that taskService.getTaskById is called once with the correct id when the task doesn't exist.
        Mockito.verify(taskService, Mockito.times(1)).getTaskById(99L);
    }

    @Test
    void updateTaskShouldReturnUpdatedTaskIfExists() throws Exception {
        Task updated = new Task(1L, "Updated Task", "Updated Desc", "Completed");
        Mockito.when(taskService.updateTask(Mockito.eq(1L), Mockito.any(Task.class)))
                .thenReturn(Optional.of(updated));

        mockMvc.perform(put("/tasks/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Updated Task\",\"description\":\"Updated Desc\",\"status\":\"Completed\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Updated Task"));

        // Verify that taskService.updateTask is called once with the correct id and a Task object containing the updated data.
        Mockito.verify(taskService, Mockito.times(1))
            .updateTask(Mockito.eq(1L), Mockito.argThat(task ->
                "Updated Task".equals(task.getTitle()) &&
                "Updated Desc".equals(task.getDescription()) &&
                "Completed".equals(task.getStatus())
            ));
    }

    @Test
    void updateTaskShouldReturnNotFoundIfDoesNotExist() throws Exception {
        Mockito.when(taskService.updateTask(Mockito.eq(99L), Mockito.any(Task.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/tasks/99")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Whatever\",\"description\":\"Doesn't matter\",\"status\":\"Pending\"}"))
            .andExpect(status().isNotFound());

        // Verify that taskService.updateTask is called once with the correct id when the task doesn't exist.
        Mockito.verify(taskService, Mockito.times(1))
            .updateTask(Mockito.eq(99L), Mockito.any(Task.class));
    }

    @Test
    void deleteTaskShouldReturnOkIfDeleted() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isNoContent());

        // Verify that taskService.deleteTask was called once with id=1
        Mockito.verify(taskService, Mockito.times(1)).deleteTask(1L);
    }
}
