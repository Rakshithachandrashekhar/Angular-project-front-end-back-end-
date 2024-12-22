package com.rakshithacodes.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rakshithacodes.todo.model.Task;
import com.rakshithacodes.todo.repository.TaskRepository;

import jakarta.persistence.Entity;

@RestController
@CrossOrigin
@RequestMapping("/api/tasks") // Base URL for all task-related endpoints
public class TaskController {

    @Autowired
    private TaskRepository repo;
    

    // Endpoint to display a welcome message
    @GetMapping("/hello")
    public String helloWorld() {
        return "Welcome to  Codes To-Do Application!";
    }

    // Endpoint to create a new task
    @PostMapping
    public Task createTask(@RequestBody Task data) {
        return repo.save(data);
    }

    // Endpoint to fetch all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    // Endpoint to update an existing task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task data) {
        // Ensure the task exists before updating
        Task existingTask = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Update task fields
        existingTask.setTitle(data.getTitle());
        existingTask.setDescription(data.getDescription());
        existingTask.setCompleted(data.isCompleted());

        return repo.save(existingTask);
    }

    // Endpoint to delete a task by ID
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
