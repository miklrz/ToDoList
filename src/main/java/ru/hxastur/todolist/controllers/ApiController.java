package ru.hxastur.todolist.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Authority;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.service.AuthorService;
import ru.hxastur.todolist.service.TaskService;

import java.util.Set;

@RestController
@RequestMapping("/api/authors")
public class ApiController {

    private final AuthorService authorService;
    private final TaskService taskService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApiController(AuthorService authorService, TaskService taskService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.authorService = authorService;
        this.taskService = taskService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping()
    public Iterable<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable int id){
        return authorService.getAuthor(id);
    }

    @PostMapping()
    public void saveAuthor(@RequestBody Author author){
        String encodedPassword = bCryptPasswordEncoder.encode(author.getPassword());
        author.setPassword(encodedPassword);
        authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public Author editAuthor(@RequestBody Author newAuthor, @PathVariable int id){
        String encodedPassword = bCryptPasswordEncoder.encode(newAuthor.getPassword());
        newAuthor.setPassword(encodedPassword);
        return authorService.editAuthor(newAuthor, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }

    @GetMapping("/{authorId}/tasks")
    public Iterable<Task> getAuthorTasks(@PathVariable int authorId){
        return taskService.getAuthorTasks(authorId);
    }

    @GetMapping("/{authorId}/tasks/{taskId}")
    public Task getTask(@PathVariable int taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping("/{authorId}/tasks")
    public void saveTask(@RequestBody Task newTask, @PathVariable int authorId){
        taskService.saveTask(newTask,authorId);
    }

    @PutMapping("/{authorId}/tasks/{taskId}")
    public void editTask(@RequestBody Task newTask, @PathVariable int taskId){
        taskService.editTask(newTask,taskId);
    }

    @DeleteMapping("/{authorId}/tasks/{taskId}")
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
    }
}
