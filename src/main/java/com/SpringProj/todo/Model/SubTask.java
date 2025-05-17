package com.SpringProj.todo.Model;

import com.SpringProj.todo.Enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "subtask")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 1, max = 50)
    @NotNull
    private String title;

    @Length(min = 1, max = 250)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToMany(mappedBy = "subtask")
    private List<SubtaskAttachment> subtaskAttachments;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private SubTask parentSubTask;

    @OneToMany(mappedBy = "parentSubTask", cascade = CascadeType.ALL)
    private List<SubTask> childSubTasks;

}
