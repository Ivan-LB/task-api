package com.belli.taskify.service;

import com.belli.taskify.model.Task;
import com.belli.taskify.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        task1 = new Task(1L, "Task One", "Description One", "Pending");
        task2 = new Task(2L, "Task Two", "Description Two", "Completed");
    }

    @Test
    void createTaskShouldReturnCreatedTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task1);

        Task created = taskService.createTask(new Task(null, "Task One", "Description One", "Pending"));

        assertNotNull(created.getId(), "Created task should have an ID");
        assertEquals("Task One", created.getTitle(), "Title should match input");
        assertEquals("Description One", created.getDescription(), "Description should match input");
        assertEquals("Pending", created.getStatus(), "Status should match input");
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void getAllTasksShouldReturnEmptyListInitially() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        List<Task> tasks = taskService.getAllTasks();
        assertTrue(tasks.isEmpty(), "Initial task list should be empty");
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getAllTaskShouldReturnListOfTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1,task2));

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size(), "There should be two tasks");
        assertTrue(tasks.contains(task1), "List should contain Task One");
        assertTrue(tasks.contains(task2), "List should contain task two");
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getTaskByIdShouldReturnTaskIfExists() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Optional<Task> found = taskService.getTaskById(1L);
        assertTrue(found.isPresent(), "Task should be found");
        assertEquals(task1.getId(), found.get().getId(), "IDs should match");
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getTaskByIdShouldReturnEmptyIfTaskDoesNotExist() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Task> found = taskService.getTaskById(99L);
        assertFalse(found.isPresent(), "Task should not be found");
        verify(taskRepository, times(1)).findById(99L);
    }

    @Test
    void updateTaskShouldReturnUpdatedTaskIfExists() {
        Task updatedTask = new Task(1L, "Updated Title", "Updated Description", "Completed");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Optional<Task> updated = taskService.updateTask(1L, new Task(null, "Updated Title", "Updated Description", "Completed"));
        assertTrue(updated.isPresent(), "Task should be updated");
        assertEquals(updatedTask.getId(), updated.get().getId(), "IDs should match");
        assertEquals("Updated Title", updated.get().getTitle(), "Title should be updated");
        assertEquals("Updated Description", updated.get().getDescription(), "Description should be updated");
        assertEquals("Completed", updated.get().getStatus(), "Status should be updated");
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void updateTaskShouldReturnEmptyIfDoesNotExist() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Task> updated = taskService.updateTask(99L, new Task(null, "Updated Task", "Updated Desc", "Completed"));
        assertFalse(updated.isPresent(), "Task should not be updated as it does not exist");
        verify(taskRepository, times(1)).findById(99L);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void deleteTaskShouldRemoveTaskIfExists() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteTaskShouldDoNothingIfDoesNotExist() {
        doNothing().when(taskRepository).deleteById(99L);

        // Attempt to delete a non-existent task; should not throw an exception
        assertDoesNotThrow(() -> taskService.deleteTask(99L), "Deleting a non-existent task should not throw an exception");
        verify(taskRepository, times(1)).deleteById(99L);
    }
}
