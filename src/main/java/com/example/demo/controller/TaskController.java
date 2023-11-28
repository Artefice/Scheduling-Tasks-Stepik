package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Task create(@RequestBody Task task) {
        task.setUser_id(currentUserId());
        return taskRepository.save(task);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Task> getAll() {
        return taskRepository.findAllByUser_id(currentUserId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task findById(@PathVariable long id) {
        return taskRepository.findByIdAndUser_id(id, currentUserId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable long id, @RequestBody Task task) {
        Task prevtask = taskRepository.findById(id).orElse(null);
        if (prevtask != null && checkUserIdByTaskId(id)) {
            if (task.getDate() != null) {
                prevtask.setDate(task.getDate());
            }
            if (!task.getDescription().equals("")) {
                prevtask.setDescription(task.getDescription());
            }
            return taskRepository.save(prevtask);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        if (checkUserIdByTaskId(id)) {
            taskRepository.deleteById(id);
        }
    }

    @RequestMapping(value = "/{id}:mark-as-done", method = RequestMethod.PATCH)
    public void setDone(@PathVariable Long id) {
        if (checkUserIdByTaskId(id)) {
            taskRepository.markAsDone(id);
        }
    }

    @RequestMapping(value = "/{id}:mark-as-undone", method = RequestMethod.PATCH)
    public void setUndone(@PathVariable Long id) {
        if (checkUserIdByTaskId(id)) {
            taskRepository.markAsUndone(id);
        }
    }

    private long currentUserId() {
        return userService.getCurrentUser().getId();
    }

    private boolean checkUserIdByTaskId(long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            return currentUserId() == task.getUser_id();
        }
        return false;
    }
}
