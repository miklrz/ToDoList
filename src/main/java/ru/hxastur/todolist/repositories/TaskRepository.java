package ru.hxastur.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hxastur.todolist.models.Task;

public interface TaskRepository extends CrudRepository<Task,Integer> {
}
