package ru.hxastur.todolist.exceptions;

public class TaskAccessPermissionDenied extends RuntimeException {
    public TaskAccessPermissionDenied(int id) {
        super("You do not have permission to view this task with id: " + id);
    }
}
