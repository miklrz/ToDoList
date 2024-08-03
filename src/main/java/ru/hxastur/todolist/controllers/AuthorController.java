package ru.hxastur.todolist.controllers;

import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.exceptions.AuthorNotFoundException;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.repositories.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @GetMapping()
    public Iterable<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable int id){
        return authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
    }

    @PostMapping()
    public void saveAuthor(@RequestBody Author author){
        authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author editAuthor(@RequestBody Author newAuthor, @PathVariable int id){
        Author author = authorRepository.findById(id).orElseGet(()->{return authorRepository.save(newAuthor);});
        author.setName(newAuthor.getName());
        author.setTaskList(newAuthor.getTaskList());
        return authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorRepository.deleteById(id);
    }
}
