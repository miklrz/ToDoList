package ru.hxastur.todolist.service;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.exceptions.TaskNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Task;
import ru.hxastur.todolist.repositories.AuthorRepository;
import ru.hxastur.todolist.repositories.TaskRepository;

import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    TaskRepository taskRepository;
    AuthorRepository authorRepository;

    public TaskService(TaskRepository taskRepository, AuthorRepository authorRepository){
        this.taskRepository = taskRepository;
        this.authorRepository = authorRepository;
    }

    public Set<Task> getAuthorTasks(int authorId){
        Author author =  authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        return author.getTaskList();
    }

    public Task getTask(int taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public void saveTask(Task newTask, int authorId){
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Task task = new Task(newTask.getTitle(),newTask.getContent(),author, newTask.isDone());
        author.getTaskList().add(task);
        taskRepository.save(task);
    }

    public void editTask(Task newTask, int taskId){
        Task task = taskRepository.findById(taskId).orElseGet(()->{
            return taskRepository.save(newTask); // ошибка?
        });
        task.setTitle(newTask.getTitle());
        task.setContent(newTask.getContent());
        taskRepository.save(task);
    }

    public void deleteTask(int taskId){
        taskRepository.deleteById(taskId);
    }
}
