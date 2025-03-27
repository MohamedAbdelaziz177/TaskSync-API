package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.DTOs.AuthResponseDto;
import com.SpringProj.todo.DTOs.LoginDto;
import com.SpringProj.todo.DTOs.RegisterDto;

public interface AuthService {

    AuthResponseDto login(LoginDto loginDto);
    AuthResponseDto register(RegisterDto registerDto);
}
