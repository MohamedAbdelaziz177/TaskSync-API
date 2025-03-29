package com.SpringProj.todo.DTOs.SubTaskDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskCreateDto {


    @NotBlank(message = "title cannot be empty")
    private String title;

    private String description;

    @NotNull(message = "tastId must be assigned")
    private Long taskId;

    @NotNull(message = "deadline must be assigned")
    private Date deadline;

}
