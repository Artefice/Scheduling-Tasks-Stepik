package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task findById(@PathVariable long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable long id, @RequestBody Task task) {
        Task prevtask = taskRepository.findById(id).orElse(null);
        if (prevtask != null) {
            if (task.getDate() != null) {
                prevtask.setDate(task.getDate());
            }
            if (!task.getDescription().equals("")) {
                prevtask.setDescription(task.getDescription());
            }
            return taskRepository.save(prevtask);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}:mark-as-done", method = RequestMethod.PATCH)
    public void setDone(@PathVariable Long id) {
        taskRepository.markAsDone(id);
    }
}
