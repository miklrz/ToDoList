package ru.hxastur.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class MainController {

    private final TaskRepository taskRepository;

    public MainController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

//    @GetMapping()
//    public @ResponseBody Iterable<Task> getAllTasks(){
//        return taskRepository.findAll();
//    }

    @GetMapping()
    public String indexTasks(Model model){
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    @ResponseBody
    public String newTask(@RequestParam(name = "title") String title){
        Task task = new Task();
        task.setTitle(title);
        taskRepository.save(task);
        return "Saved!";
    }

}
