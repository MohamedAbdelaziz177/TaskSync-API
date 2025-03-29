package com.SpringProj.todo.DTOs.AuthDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "first name is required")
    @Min(4)
    @Max(20)
    private String firstName;

    @NotBlank(message = "last name is required")
    @Min(4)
    @Max(20)
    private String lastName;

    @Email(message = "Enter valid email")
    private String email;


    private String password;

    private String confirmPassword;
}
