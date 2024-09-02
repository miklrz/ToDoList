package ru.hxastur.todolist.controllers;

import org.springframework.web.bind.annotation.*;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping()
    public Iterable<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable int id){
        return authorService.getAuthor(id);
    }

    @PostMapping()
    public void saveAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public Author editAuthor(@RequestBody Author newAuthor, @PathVariable int id){
        return authorService.editAuthor(newAuthor, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }
}
