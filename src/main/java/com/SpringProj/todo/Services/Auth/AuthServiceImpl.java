package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.DTOs.AuthResponseDto;
import com.SpringProj.todo.DTOs.LoginDto;
import com.SpringProj.todo.DTOs.RegisterDto;
import com.SpringProj.todo.Model.Role;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.UserRepository;
import com.SpringProj.todo.Services.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthResponseDto login(LoginDto loginDto) {

        try {

            Authentication authToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            Authentication authRes = authenticationManager.authenticate(authToken);

            UserDetails user = userDetailsService.loadUserByUsername(loginDto.getEmail());

            String token = jwtService.generateToken(user, new HashMap<>());

            return AuthResponseDto.builder()
                    .token(token)
                    .username(user.getUsername())
                    .isAuthenticated(true)
                    .expires(jwtService.getExpiry())
                    .build();

        } catch (AuthenticationException e) {

            return AuthResponseDto.builder()
                    .isAuthenticated(false)
                    .message(e.getMessage())
                    .build();
        }

    }


    public AuthResponseDto register(RegisterDto registerDto) {

        try{

            User user = User.builder()
                    .email(registerDto.getEmail())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .firstName(registerDto.getFirstName())
                    .lastName(registerDto.getLastName())
                    .roles(new ArrayList<>())
                    .build();

            userRepository.save(user);

            return AuthResponseDto.builder()
                    .message("User Registered Successfully - plz check ur email for confirmation")
                    .isAuthenticated(true)
                    .build();

        }catch (Exception e)
        {
            return AuthResponseDto.builder()
                    .isAuthenticated(false)
                    .message(e.getMessage())
                    .build();
        }

    }


}
