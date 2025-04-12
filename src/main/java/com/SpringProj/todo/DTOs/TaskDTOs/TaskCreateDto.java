package com.SpringProj.todo.DTOs.TaskDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto {

    @NotBlank(message = "title cannot be empty")
    @Length(min = 1, max = 50, message = "length must be between 1 and 50")
    private String title;

    @Length(min = 10, max = 350, message = "length must be between 10 and 350")
    private String description;

    private String priority;

    @NotNull
    private Date deadline;

    private Long categoryId;

    private String status = "PENDING";
}
