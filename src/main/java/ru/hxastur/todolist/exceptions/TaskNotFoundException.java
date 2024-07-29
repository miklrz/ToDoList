package ru.hxastur.todolist.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(int id){
        super("Could not find task "+ id);
    }
}
