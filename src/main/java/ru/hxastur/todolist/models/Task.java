package ru.hxastur.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Task")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Size(min = 1, max = 20, message = "title should be grater than 1 and less than 20 symbols")
    @NotNull(message="title should not be empty")
    private String title;

    @Column(name="content")
    @Size(max = 100, message="content should be shorter than 100 symbols")
    @NotNull(message="content should not be empty")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private Author taskAuthor;

    @Column(name="isDone")
    private boolean isDone;

    public Task(String title, String content, Author author, boolean isDone) {
        this.title = title;
        this.content = content;
        this.taskAuthor = author;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", taskAuthor=" + taskAuthor +
                ", isDone=" + isDone +
                '}';
    }
}
