package com.belli.taskify.service;

import com.belli.taskify.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceImplTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl();
    }

    @Test
    void createTaskShouldReturnCreatedTask() {
        Task input = new Task(null, "New Task", "Description", "Pending");
        Task created = taskService.createTask(input);

        assertNotNull(created.getId(), "Created task should have an ID");
        assertEquals("New Task", created.getTitle(), "Title should match input");
        assertEquals("Description", created.getDescription(), "Description should match input");
        assertEquals("Pending", created.getStatus(), "Status should match input");
    }

    @Test
    void getAllTasksShouldReturnEmptyListInitially() {
        List<Task> tasks = taskService.getAllTasks();
        assertTrue(tasks.isEmpty(), "Initial task list should be empty");
    }

    @Test
    void getAllTaskShouldReturnListOfTasks() {
        Task t1 = taskService.createTask(new Task(null, "Task One", "Desc One", "Pending"));
        Task t2 = taskService.createTask(new Task(null, "Task Two", "Desc Two", "Completed"));

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size(), "There should be two tasks");
        assertTrue(tasks.contains(t1), "List should contain Task One");
        assertTrue(tasks.contains(t2), "List should contain task two");
    }

    @Test
    void getTaskByIdShouldReturnTaskIfExists() {
        Task created = taskService.createTask(new Task(null, "Task One", "Description", "Pending"));

        Optional<Task> found = taskService.getTaskById(created.getId());
        assertTrue(found.isPresent(), "Task should be found");
        assertEquals(created.getId(), found.get().getId(), "IDs should match");
    }

    @Test
    void getTaskByIdShouldReturnEmptyIfTaskDoesNotExist() {
        Optional<Task> found = taskService.getTaskById(99L);
        assertFalse(found.isPresent(), "Task should not be found");
    }

    @Test
    void updateTaskShouldReturnUpdatedTaskIfExists() {
        Task created = taskService.createTask(new Task(null, "Original Task", "Original Description", "Pending"));
        Task update = new Task(null, "Updated Title", "Updated Description", "Completed");

        Optional<Task> updated = taskService.updateTask(created.getId(), update);
        assertTrue(updated.isPresent(), "Task should be updated");
        assertEquals(created.getId(), updated.get().getId(), "IDs should match");
        assertEquals("Updated Title", updated.get().getTitle(), "Title should be updated");
        assertEquals("Updated Description", updated.get().getDescription(), "Description should be updated");
        assertEquals("Completed", updated.get().getStatus(), "Status should be updated");
    }

    @Test
    void updateTaskShouldReturnEmptyIfDoesNotExist() {
        Task update = new Task(null, "Updated Task", "Updated Desc", "Completed");

        Optional<Task> updated = taskService.updateTask(99L, update);
        assertFalse(updated.isPresent(), "Task should not be updated as it does not exist");
    }

    @Test
    void deleteTaskShouldRemoveTaskIfExists() {
        Task created = taskService.createTask(new Task(null, "Task to Delete", "Desc", "Pending"));

        taskService.deleteTask(created.getId());

        Optional<Task> found = taskService.getTaskById(created.getId());
        assertFalse(found.isPresent(), "Task should be deleted");
    }

    @Test
    void deleteTaskShouldDoNothingIfDoesNotExist() {
        // Attempt to delete a non-existent task; should not throw an exception
        assertDoesNotThrow(() -> taskService.deleteTask(99L), "Deleting a non-existent task should not throw an exception");
    }
}
