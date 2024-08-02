package ru.hxastur.todolist.controllers;

import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.exceptions.TaskNotFoundException;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping()
    public Iterable<Task> getAllTasks(){
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
    public void saveTask(@RequestBody Task task){
        taskRepository.save(task);
    }

    @PutMapping
    public Task editTask(@RequestBody int id, @RequestBody Task newTask){
//        return taskRepository.findById(id).map(task -> {
//            task.setTitle(newTask.getTitle());
//            return taskRepository.save(task);
//        }).orElseGet(() -> {
//            return taskRepository.save(newTask);
//        });
        Task task = taskRepository.findById(id).orElseGet(()->{return taskRepository.save(newTask);});
        task.setTitle(newTask.getTitle());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        taskRepository.deleteById(id);
    }

}
