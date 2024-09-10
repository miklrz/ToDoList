package ru.hxastur.todolist.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.security.AuthorDetails;
import ru.hxastur.todolist.service.TaskService;


@Controller
@RequestMapping("/tasks")
public class UserController {
    private final TaskService taskService;

    public UserController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping()
    public String getAuthorTasks(Model model, @AuthenticationPrincipal AuthorDetails authorDetails){
        model.addAttribute("taskList", taskService.getAuthorTasks(authorDetails.getAuthor().getId()));
        model.addAttribute("author", authorDetails.getAuthor().getId());
        return "tasks/index";
    }

    @GetMapping("/{taskId}")
    public String getTask(@PathVariable int taskId, Model model,
                          @AuthenticationPrincipal AuthorDetails authorDetails){
        model.addAttribute("task", taskService.getTask(taskId));
        model.addAttribute("authorId", authorDetails.getAuthor().getId());
        return "tasks/task";
    }

    @GetMapping("/new")
    public String newTaskPage(Model model, @ModelAttribute("task") Task task,
                              @AuthenticationPrincipal AuthorDetails authorDetails){
        model.addAttribute("authorId", authorDetails.getAuthor().getId());
        return "tasks/new";
    }

    @GetMapping("/{taskId}/edit")
    public String editTaskPage(Model model, @PathVariable int taskId,
                               @AuthenticationPrincipal AuthorDetails authorDetails){
        model.addAttribute("authorId", authorDetails.getAuthor().getId());
        model.addAttribute("task", taskService.getTask(taskId));
        return "tasks/edit";
    }

    @PostMapping()
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                           Model model, @AuthenticationPrincipal AuthorDetails authorDetails){
        if(bindingResult.hasErrors()){
            model.addAttribute("authorId", authorDetails.getAuthor().getId());
            model.addAttribute("taskId", task.getId());
            return "tasks/new";
        }
        taskService.saveTask(task,authorDetails.getAuthor().getId());
        return "redirect:/tasks";
    }

    @PutMapping("/{taskId}")
    public String editTask(@Valid @ModelAttribute("task") Task newTask, BindingResult bindingResult,
                           @PathVariable int taskId, Model model,
                           @AuthenticationPrincipal AuthorDetails authorDetails){
        if(bindingResult.hasErrors()){
            model.addAttribute("authorId", authorDetails.getAuthor().getId());
            model.addAttribute("taskId", taskId);
            return "tasks/edit";
        }
        taskService.editTask(newTask,taskId);
        return "redirect:/tasks";
    }

    @DeleteMapping("authors/{authorId}/tasks/{taskId}")
    public String deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }
}
