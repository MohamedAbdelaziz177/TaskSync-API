package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.DTOs.AuthDTOs.ConfirmEmailDto;
import com.SpringProj.todo.DTOs.AuthDTOs.ResetPasswordDto;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;
import com.SpringProj.todo.Responses.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse login(HttpServletResponse response, LoginDto loginDto);
    AuthResponse register(RegisterDto registerDto);
    TokenResponse refreshToken(HttpServletRequest request, HttpServletResponse response);
    void sendOtpToUser(User user);
    void confirmEmail(ConfirmEmailDto confirmEmailDto);
    void resetPassword(ResetPasswordDto resetPasswordDto);
    void sendOtpToUser(String email);
}
