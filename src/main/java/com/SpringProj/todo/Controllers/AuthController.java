package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;
import com.SpringProj.todo.Responses.TokenResponse;
import com.SpringProj.todo.Services.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> Register(@RequestBody RegisterDto registerDto) {

        try{

            AuthResponse authResponse = authService.register(registerDto);

            if(!authResponse.isAuthenticated())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);

            return ResponseEntity.status(HttpStatus.OK).body(authResponse);
        }
        catch (Exception e)
        {
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .isAuthenticated(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
        }

    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody LoginDto loginDto) {

        try {

            AuthResponse authResponse = authService.login(loginDto);

            if(!authResponse.isAuthenticated())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);



            return ResponseEntity.status(HttpStatus.OK).body(authResponse);
        }
        catch (NoSuchElementException e)
        {
            AuthResponse authResponse = AuthResponse.builder()
                    .message("User not found")
                    .isAuthenticated(false)
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
        }
        catch (Exception e)
        {
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .isAuthenticated(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
        }

    }

    // Refresh token

    @GetMapping("/refresh-token")
    public ResponseEntity<TokenResponse> RefreshToken() {

        try{

            // get Ref token from cookie
            TokenResponse tokenResponse =  authService.refreshToken("ref-token");
            return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
        }
        catch (NoSuchElementException e)
        {
            TokenResponse tokenResponse = TokenResponse.builder()
                    .success(false)
                    .build();

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(tokenResponse);
        }
        catch (Exception e)
        {
            TokenResponse tokenResponse = TokenResponse.builder()
                    .success(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tokenResponse);
        }

    }

    // logout



}
