package ru.hxastur.todolist.models;

import jakarta.persistence.*;

@Entity
@Table(name="Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private Author author;

    public Task(){}

    public Task(int id,String title) {
        this.id = id;
        this.title = title;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
