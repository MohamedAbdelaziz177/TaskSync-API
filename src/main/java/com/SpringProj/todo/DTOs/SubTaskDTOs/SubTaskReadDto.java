package com.SpringProj.todo.DTOs.SubTaskDTOs;

import com.SpringProj.todo.Model.SubTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskReadDto {

    public SubTaskReadDto(SubTask subTask) {
        this.id = subTask.getId();
        this.title = subTask.getTitle();
        this.description = subTask.getDescription();
        this.deadline = subTask.getTask().getDeadline();
        this.status = subTask.getStatus().toString();
    }

    private Long id;

    private String title;

    private String description;

    private Long taskId;

    private Date deadline;

    private String status;
}
