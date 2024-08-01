package ru.hxastur.todolist.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="name")
    String name;

    @OneToMany(mappedBy = "author")
    private ArrayList<Task> taskList;

    public Author() {}
    public Author(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
