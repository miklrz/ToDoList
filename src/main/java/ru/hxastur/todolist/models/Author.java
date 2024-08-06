package ru.hxastur.todolist.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="name")
    @Size(min=2, max = 20, message="name should be greater than 2 and less than 20")
    String name;

    @OneToMany(mappedBy = "author")
    Set<Task> taskList = new HashSet<>();
}
