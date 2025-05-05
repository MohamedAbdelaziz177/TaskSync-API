package com.SpringProj.todo.Controllers;

import com.SpringProj.todo.DTOs.AuthDTOs.ConfirmEmailDto;
import com.SpringProj.todo.DTOs.AuthDTOs.ResetPasswordDto;
import com.SpringProj.todo.Exceptions.CodeNotValidException;
import com.SpringProj.todo.Exceptions.PasswordsNotMatchedException;
import com.SpringProj.todo.Responses.ApiResponse;
import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;
import com.SpringProj.todo.Responses.TokenResponse;
import com.SpringProj.todo.Services.Auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

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
        catch (AuthenticationServiceException e)
        {
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .isAuthenticated(false)
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .isAuthenticated(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
        }

    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(HttpServletResponse response, @RequestBody LoginDto loginDto) {

        try {

            AuthResponse authResponse = authService.login(response, loginDto);

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


    @GetMapping("/refresh-token")
    public ResponseEntity<TokenResponse> RefreshToken(HttpServletRequest request, HttpServletResponse response) {

        try{

            // get Ref token from cookie / authHeader / Request param

            TokenResponse tokenResponse =  authService.refreshToken(request, response);
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
            System.out.println(e.getMessage());
            TokenResponse tokenResponse = TokenResponse.builder()
                    .success(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tokenResponse);
        }

    }


    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> ResetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {

        ApiResponse<String> res = new ApiResponse<>();
        try{

            authService.resetPassword(resetPasswordDto);
            res.setSuccess(true);
            res.setData("Password reset successfully");

            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (PasswordsNotMatchedException | CodeNotValidException e)
        {
            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

    }

    @PostMapping("/confirm-email")
    public ResponseEntity<ApiResponse<String>> ConfirmEmail(@RequestBody ConfirmEmailDto confirmEmailDto) {

        ApiResponse<String> res = new ApiResponse<>();
        try{

            authService.confirmEmail(confirmEmailDto);
            res.setSuccess(true);
            res.setData("Email confirmed successfully");

            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (CodeNotValidException e)
        {
            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @PostMapping("/resend-confirmation-code")
    public ResponseEntity<ApiResponse<String>> ResendConfirmationCode(@RequestParam String email /*must be in req body*/) {

        ApiResponse<String> res = new ApiResponse<>();

        try{

            authService.sendOtpToUser(email);

            res.setSuccess(true);
            res.setData("Confirmation code sent successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (NoSuchElementException e)
        {
            res.setSuccess(false);
            res.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

}
