package ru.hxastur.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClassNotFoundAdvice {
    //When an TaskNotFoundException is thrown, this extra tidbit of Spring MVC configuration is used to render an HTTP 404 error:

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskNotFoundHandler(TaskNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String authorNotFoundHandler(AuthorNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(TaskAccessPermissionDenied.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskAccessPermissionDenied(TaskAccessPermissionDenied ex){return ex.getMessage();}

}
