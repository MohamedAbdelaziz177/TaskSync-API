package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.DTOs.AuthDTOs.ConfirmEmailDto;
import com.SpringProj.todo.DTOs.AuthDTOs.ResetPasswordDto;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;
import com.SpringProj.todo.Responses.TokenResponse;

public interface AuthService {

    AuthResponse login(LoginDto loginDto);
    AuthResponse register(RegisterDto registerDto);
    TokenResponse refreshToken(String refreshToken);
    void sendOtpToUser(User user);
    Boolean confirmEmail(ConfirmEmailDto confirmEmailDto);
    Boolean resetPassword(ResetPasswordDto resetPasswordDto);
}
