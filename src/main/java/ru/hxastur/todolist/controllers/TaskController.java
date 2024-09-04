package ru.hxastur.todolist.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.exceptions.TaskNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.AuthorRepository;
import ru.hxastur.todolist.repositories.TaskRepository;
import ru.hxastur.todolist.service.TaskService;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getAuthorTasks(@RequestBody int authorId, Model model){
        model.addAttribute("taskList", taskService.getAuthorTasks(authorId));
        return "tasks";
    }

    @GetMapping("/tasks/{taskId}")
    public String getTask(@PathVariable int taskId, Model model){
        model.addAttribute("task", taskService.getTask(taskId));
        return "task";
    }

    @PostMapping("/tasks")
    public String saveTask(@Valid @RequestBody Task newTask, @RequestBody int authorId){
        taskService.saveTask(newTask,authorId);
        return "redirect:/tasks";
    }

    @PutMapping("/tasks/{taskId}")
    public String editTask(@Valid @RequestBody Task newTask, @PathVariable int taskId){
        taskService.editTask(newTask,taskId);
        return "redirect:/tasks";
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
    }
}
