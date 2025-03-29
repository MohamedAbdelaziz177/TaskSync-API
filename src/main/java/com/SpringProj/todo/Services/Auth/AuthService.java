package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.DTOs.AuthDTOs.AuthResponseDto;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;

public interface AuthService {

    AuthResponseDto login(LoginDto loginDto);
    AuthResponseDto register(RegisterDto registerDto);
}
