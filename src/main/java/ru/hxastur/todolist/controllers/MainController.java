package ru.hxastur.todolist.controllers;

import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.exceptions.TaskNotFoundException;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.TaskRepository;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class MainController {

    private final TaskRepository taskRepository;

    public MainController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping()
    public @ResponseBody Iterable<Task> getAllTasks(){
        return taskRepository.findAll();
    }

//    @GetMapping()
//    public String indexTasks(Model model){
//        model.addAttribute("tasks", taskRepository.findAll());
//        return "index";
//    }

    @GetMapping("/add")
    public String newTask(@RequestParam(name = "title") String title){
        Task task = new Task();
        task.setTitle(title);
        taskRepository.save(task);
        return "Saved!";
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id){
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping
    public void saveTask(@RequestBody Task newTask){
        taskRepository.save(newTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        taskRepository.deleteById(id);
    }

}
