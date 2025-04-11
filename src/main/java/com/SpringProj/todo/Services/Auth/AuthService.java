package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;

public interface AuthService {

    AuthResponse login(LoginDto loginDto);
    AuthResponse register(RegisterDto registerDto);
}
