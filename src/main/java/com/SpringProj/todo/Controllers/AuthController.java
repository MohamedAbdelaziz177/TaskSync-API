package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;
import com.SpringProj.todo.Services.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody RegisterDto registerDto) {

        AuthResponse authResponse = authService.register(registerDto);

        if(!authResponse.isAuthenticated())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);

        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto) {

        AuthResponse authResponse = authService.login(loginDto);

        if(!authResponse.isAuthenticated())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);

        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    // Refresh token

    // logout



}
