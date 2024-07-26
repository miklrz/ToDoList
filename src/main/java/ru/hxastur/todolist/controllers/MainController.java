package ru.hxastur.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.TaskRepository;

@Controller
public class MainController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "/")
    String hello(){
        return "hello!";
    }

    @PostMapping(path = "/add")
    public @ResponseBody String newTask(@RequestParam String title){
        Task task = new Task();
        task.setTitle("new task 123");
        taskRepository.save(task);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Task> getAllTasks(){
        return taskRepository.findAll();
    }

}
