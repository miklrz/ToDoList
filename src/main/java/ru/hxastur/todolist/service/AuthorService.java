package ru.hxastur.todolist.service;

import org.springframework.stereotype.Service;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.repositories.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthor(int id){
        return authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
    }

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public Author editAuthor(Author newAuthor, int id){
        Author author = authorRepository.findById(id).orElseGet(()->{return authorRepository.save(newAuthor);});
        author.setName(newAuthor.getName());
        author.setTaskList(newAuthor.getTaskList());
        return authorRepository.save(author);
    }

    public void deleteAuthor(int id){
        authorRepository.deleteById(id);
    }
}
