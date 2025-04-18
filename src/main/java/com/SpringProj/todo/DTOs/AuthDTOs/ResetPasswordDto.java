package com.SpringProj.todo.DTOs.AuthDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDto {

    private String email;
    private String password;
    private String confirmPassword;
    private Long otp;
}
