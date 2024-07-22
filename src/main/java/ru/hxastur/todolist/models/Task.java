package ru.hxastur.todolist.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @Column(name="id")
    int id;

    @Column(name = "title")
    String title;

    Task(){}

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    int getId(){
        return id;
    }
    void setId(int id){
        this.id = id;
    }

    String getTitle(){
        return title;
    }
    void setTitle(String title){
        this.title = title;
    }
}
