package com.belli.taskify.service;

import com.belli.taskify.model.Task;
import com.belli.taskify.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        return taskRepository.findById(id).map(existingTask ->{
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            return taskRepository.save(existingTask);
        });
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
