package ru.hxastur.todolist.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
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
//@RequestMapping("/user")
public class UserController {
    private final TaskService taskService;

    public UserController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("authors/{authorId}/tasks")
    public String getAuthorTasks(@PathVariable int authorId,Model model){
        model.addAttribute("taskList", taskService.getAuthorTasks(authorId));
        model.addAttribute("authorId", authorId);
        return "tasks";
    }

    @GetMapping("authors/{authorId}/tasks/{taskId}")
    public String getTask(@PathVariable int taskId, Model model, @PathVariable int authorId){
        model.addAttribute("task", taskService.getTask(taskId));
        model.addAttribute("authorId", authorId);
        return "task";
    }

    @PostMapping("authors/{authorId}/tasks")
    public String saveTask(@Valid @RequestBody Task newTask, @PathVariable int authorId){
        taskService.saveTask(newTask,authorId);
        return "redirect:/tasks";
    }

    @PutMapping("authors/{authorId}/tasks/{taskId}")
    public String editTask(@Valid @RequestBody Task newTask, @PathVariable int taskId){
        taskService.editTask(newTask,taskId);
        return "redirect:/tasks";
    }

    @DeleteMapping("authors/{authorId}/tasks/{taskId}")
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
    }
}
