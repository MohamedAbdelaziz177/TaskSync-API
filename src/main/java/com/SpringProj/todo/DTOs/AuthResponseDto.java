package com.SpringProj.todo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AuthResponseDto {

    private String username;
    private String token;
    private Date expires;

    private boolean isAuthenticated;

    // if it errors
    private String message = null;
}
