package com.SpringProj.todo.DTOs.AuthDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmEmailDto {

    //@NotNull
    private String email;

    //@NotNull
    private Long otp;
}
