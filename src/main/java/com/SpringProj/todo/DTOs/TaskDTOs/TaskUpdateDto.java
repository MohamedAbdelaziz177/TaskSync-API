package com.SpringProj.todo.DTOs.TaskDTOs;

import jakarta.persistence.Entity;
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
public class TaskUpdateDto {

    @NotBlank
    @NotNull
    @Length(min = 1, max = 50)
    private String title;

    @Length(min = 10, max = 350)
    private String description;

    private String priority;


    private String category;

    private String status;
}
