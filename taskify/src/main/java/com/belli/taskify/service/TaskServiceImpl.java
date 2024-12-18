package com.belli.taskify.service;

import com.belli.taskify.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskServiceImpl implements TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Task createTask(Task task) {
        long newId = idGenerator.incrementAndGet();
        Task newTask = new Task(newId, task.getTitle(), task.getDescription(), task.getStatus());
        tasks.put(newId, newTask);
        return newTask;
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        if(!tasks.containsKey(id)) {
            return Optional.empty();
        }
        Task updated = new Task(id, task.getTitle(), task.getDescription(), task.getStatus());
        tasks.put(id, updated);
        return Optional.of(updated);
    }

    @Override
    public void deleteTask(Long id) {
        tasks.remove(id);
    }
}
