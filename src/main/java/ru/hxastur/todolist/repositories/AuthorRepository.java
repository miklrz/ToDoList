package ru.hxastur.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hxastur.todolist.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Author findByName(String name);
}
