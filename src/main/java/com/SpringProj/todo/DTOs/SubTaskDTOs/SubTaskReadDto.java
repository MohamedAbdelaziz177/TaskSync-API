package com.SpringProj.todo.DTOs.SubTaskDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskReadDto {

    private Long id;

    private String title;

    private String description;

    private Long taskId;

    private Date deadline;
}
