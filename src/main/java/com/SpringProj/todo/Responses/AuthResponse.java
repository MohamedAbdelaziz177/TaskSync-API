package com.SpringProj.todo.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String username;
    private String token;
    private String refreshToken;

    private Date expires;

    private boolean isAuthenticated;

    // if it errors
    private String message = null;
}
