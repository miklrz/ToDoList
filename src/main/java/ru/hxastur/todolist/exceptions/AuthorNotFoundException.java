package ru.hxastur.todolist.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(int id){
        super("Could not find task " + id);
    }
}
