package ru.hxastur.todolist.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.exceptions.TaskNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.AuthorRepository;
import ru.hxastur.todolist.repositories.TaskRepository;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;
    private final AuthorRepository authorRepository;

    public TaskController(TaskRepository taskRepository, AuthorRepository authorRepository){
        this.taskRepository = taskRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors/{authorId}/tasks")
    public Iterable<Task> getAllTasks(@PathVariable int authorId){
        Author author =  authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        return author.getTaskList();
    }

    @GetMapping("/authors/{authorId}/tasks/new")
    public String newTask(@RequestParam(name = "title") String title, @PathVariable int authorId){
        Author author = authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        Task task = new Task();
        task.setTitle(title);
        author.getTaskList().add(task);
        taskRepository.save(task);
        return "Saved!";
    }

    @GetMapping("/authors/{authorId}/tasks/{id}")
    public Task getTask(@PathVariable int id){
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping("/authors/{authorId}/tasks")
    public void saveTask(@RequestBody Task newTask, @PathVariable int authorId){
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Task task = new Task(newTask.getTitle(),newTask.getContent(),author);
        author.getTaskList().add(task);
        taskRepository.save(task);
    }

    @PutMapping("/authors/{authorId}/tasks/{id}")
    public Task editTask(@RequestBody Task newTask, @PathVariable int id){
        Task task = taskRepository.findById(id).orElseGet(()->{return taskRepository.save(newTask);});
        task.setTitle(newTask.getTitle());
        return taskRepository.save(task);
    }

    @DeleteMapping("/authors/{authorId}/tasks/{id}")
    public void deleteTask(@PathVariable int id){
        taskRepository.deleteById(id);
    }
}
