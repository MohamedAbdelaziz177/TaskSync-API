package com.SpringProj.todo.Model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "subtask")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 1, max = 50)
    private String title;

    @Length(min = 1, max = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
