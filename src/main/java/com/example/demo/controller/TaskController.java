package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping(value = {"/tasks", "/tasks/{id}"})
    public Iterable<Task> getTasks(@PathVariable(required = false) Long id) {
        if (id != null) {
            List<Task> tasks = new ArrayList<>();
            tasks.add(taskRepository.findById(id).orElse(null));
            return tasks;
        }else {
            return taskRepository.findAll();
        }
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable(value = "id") Long id, @Valid @RequestBody Task taskDetails) {
        taskDetails.setId(id);
        return taskRepository.save(taskDetails);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable(value = "id") Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id) {
        taskRepository.markAsDone(id);
    }
}
