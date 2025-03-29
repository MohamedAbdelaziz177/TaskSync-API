package com.SpringProj.todo.DTOs.TaskDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReadDto {

    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    private String category;



}
