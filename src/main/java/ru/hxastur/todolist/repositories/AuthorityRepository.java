package ru.hxastur.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Authority;

import java.util.Set;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Set<Authority> getAllByAuthorityAuthor(Author author);
}
