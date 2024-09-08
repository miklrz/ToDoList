package ru.hxastur.todolist.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(int id){
        super("Could not find author " + id);
    }

    public AuthorNotFoundException(String username){
        super("Could not find author " + username);
    }
}
