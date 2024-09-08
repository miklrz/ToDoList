package ru.hxastur.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.repositories.AuthorRepository;
import ru.hxastur.todolist.security.AuthorDetails;

@Service
public class MyDatabaseAuthorDetailsService implements UserDetailsService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorRepository.findByName(username);
        if(author == null) throw new AuthorNotFoundException(username);
        return new AuthorDetails(author);
    }
}
