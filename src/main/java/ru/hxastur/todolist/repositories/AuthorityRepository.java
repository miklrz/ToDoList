package ru.hxastur.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hxastur.todolist.models.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
}
