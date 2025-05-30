package com.SpringProj.todo.Model;

import com.SpringProj.todo.Enums.TaskPriority;
import com.SpringProj.todo.Enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 1, max = 50)
    private String title;

    //@Column(length = 300)
    @Length(min = 10, max = 250)
    private String description;

    @Column(name = "created_at")
    private Date CreatedAt;

    @Column(name = "deadline")
    private Date Deadline;


    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskPriority priority;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subTasks;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private  Category category;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task")
    private List<TaskAttachment> taskAttachments;
}
