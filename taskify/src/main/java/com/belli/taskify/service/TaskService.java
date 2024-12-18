package com.belli.taskify.service;

import com.belli.taskify.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Optional<Task> updateTask(Long id, Task task);
    void deleteTask(Long id);
}
