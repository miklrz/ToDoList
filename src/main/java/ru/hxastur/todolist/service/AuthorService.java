package ru.hxastur.todolist.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Authority;
import ru.hxastur.todolist.repositories.AuthorRepository;
import ru.hxastur.todolist.repositories.AuthorityRepository;

import java.util.Set;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthorService(AuthorRepository authorRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorityRepository authorityRepository){
        this.authorRepository = authorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthor(int id){
        return authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
    }

    public void saveAuthor(Author author){
        String encodedPassword = bCryptPasswordEncoder.encode(author.getPassword());
        author.setPassword(encodedPassword);
        author.getAuthorities().forEach(authority -> authority.setAuthorityAuthor(author));
        authorRepository.save(author);
    }

    public Author editAuthor(Author newAuthor, int id){
        Author author = authorRepository.findById(id).orElseGet(()->{return authorRepository.save(newAuthor);});
        author.setName(newAuthor.getName());
        author.setTaskList(newAuthor.getTaskList());
        String encodedPassword = bCryptPasswordEncoder.encode(newAuthor.getPassword());
        author.setPassword(encodedPassword);

        author.getAuthorities().clear();
        Set<Authority> authorities = authorityRepository.getAllByAuthorityAuthor(author);
        authorityRepository.getAllByAuthorityAuthor(author).forEach(authority -> authorityRepository.deleteById(authority.getId()));
        newAuthor.getAuthorities().forEach(authority -> authority.setAuthorityAuthor(author));
        for(Authority authority : newAuthor.getAuthorities()){
            authority.setAuthorityAuthor(author);
            authorityRepository.save(authority);
            author.getAuthorities().add(authority);
        }
        return authorRepository.save(author);
    }

    public void deleteAuthor(int id){
        authorRepository.deleteById(id);
    }
}
