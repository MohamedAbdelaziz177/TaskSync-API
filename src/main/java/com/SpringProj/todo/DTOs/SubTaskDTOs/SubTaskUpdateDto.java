package com.SpringProj.todo.DTOs.SubTaskDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskUpdateDto {


    @NotBlank(message = "title cannot be empty")
    private String title;


    private String description;

    private String status;

    @NotNull(message = "task Id must be assigned")
    private Long TaskId;
}
