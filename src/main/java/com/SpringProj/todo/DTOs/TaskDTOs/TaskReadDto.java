package com.SpringProj.todo.DTOs.TaskDTOs;

import com.SpringProj.todo.Model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReadDto {

    public TaskReadDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority().toString();
        this.status = task.getStatus().toString();
        this.category = task.getCategory().getCategory();
    }

    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    private String category;


}
