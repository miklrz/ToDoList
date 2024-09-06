package ru.hxastur.todolist.controllers;

import jakarta.validation.Valid;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.service.TaskService;

@Controller
//@RequestMapping("/user")
public class UserController {
    // Вместо обращения по authorId в будущем нужно использовать Authorization
    //
    private final TaskService taskService;

    public UserController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("authors/{authorId}/tasks")
    public String getAuthorTasks(@PathVariable int authorId,Model model){
        model.addAttribute("taskList", taskService.getAuthorTasks(authorId));
        model.addAttribute("author", authorId);
        return "tasks/index";
    }

    @GetMapping("authors/{authorId}/tasks/{taskId}")
    public String getTask(@PathVariable int taskId, Model model, @PathVariable int authorId){
        model.addAttribute("task", taskService.getTask(taskId));
        model.addAttribute("authorId", authorId);
        return "tasks/task";
    }

    @GetMapping("authors/{authorId}/tasks/new")
    public String newTaskPage(@PathVariable int authorId, Model model, @ModelAttribute("task") Task task){
        model.addAttribute("authorId", authorId);
        return "tasks/new";
    }

    @GetMapping("/authors/{authorId}/tasks/{taskId}/edit")
    public String editTaskPage(@PathVariable int authorId, Model model, @PathVariable int taskId){
        model.addAttribute("authorId", authorId);
        model.addAttribute("task", taskService.getTask(taskId));
        return "tasks/edit";
    }

    @PostMapping("authors/{authorId}/tasks")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, @PathVariable int authorId, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("authorId", authorId);
            model.addAttribute("taskId", task.getId());
            return "tasks/new";
        }
        taskService.saveTask(task,authorId);
        return "redirect:/authors/{authorId}/tasks";
    }

    @PutMapping("authors/{authorId}/tasks/{taskId}")
    public String editTask(@Valid @ModelAttribute("task") Task newTask, BindingResult bindingResult, @PathVariable int taskId, Model model, @PathVariable int authorId){
        if(bindingResult.hasErrors()){
            model.addAttribute("authorId", authorId);
            model.addAttribute("taskId", taskId);
            return "tasks/edit";
        }
        taskService.editTask(newTask,taskId);
        return "redirect:/authors/{authorId}/tasks";
    }

    @DeleteMapping("authors/{authorId}/tasks/{taskId}")
    public String deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
        return "redirect:/authors/{authorId}/tasks";
    }
}
