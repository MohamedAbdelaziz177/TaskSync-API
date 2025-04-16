package com.SpringProj.todo.Services.Auth;

import com.SpringProj.todo.DTOs.AuthDTOs.ConfirmEmailDto;
import com.SpringProj.todo.DTOs.AuthDTOs.ResetPasswordDto;
import com.SpringProj.todo.Exceptions.CodeNotValidException;
import com.SpringProj.todo.Exceptions.PasswordsNotMatchedException;
import com.SpringProj.todo.Responses.AuthResponse;
import com.SpringProj.todo.DTOs.AuthDTOs.LoginDto;
import com.SpringProj.todo.DTOs.AuthDTOs.RegisterDto;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.UserRepository;
import com.SpringProj.todo.Responses.TokenResponse;
import com.SpringProj.todo.Services.Jwt.JwtService;
import com.SpringProj.todo.Services.Mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthResponse login(LoginDto loginDto) {

            Authentication authToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());


            Authentication authRes = authenticationManager.authenticate(authToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());

            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() ->
                    new NoSuchElementException("No such user found"));

            //String token = jwtService.generateToken(user, new HashMap<>());
            TokenResponse tokenResponse = jwtService.getTokens(user);

            return AuthResponse.builder()
                    .token(tokenResponse.getAccessToken())
                    .username(user.getUsername())
                    .refreshToken(tokenResponse.getRefreshToken())
                    .isAuthenticated(true)
                    .expires(jwtService.getExpiry())
                    .build();


    }

    public AuthResponse register(RegisterDto registerDto) {

            Optional<User> user1 = userRepository.findByEmail(registerDto.getEmail());

            if(user1.isPresent())
                throw new AuthenticationServiceException("User already exists");


            User user = User.builder()
                    .email(registerDto.getEmail())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .firstName(registerDto.getFirstName())
                    .lastName(registerDto.getLastName())
                    .roles(new ArrayList<>())
                    .build();


            userRepository.save(user);

            sendOtpToUser(registerDto.getEmail());

            return AuthResponse.builder()
                    .message("User Registered Successfully - plz check ur email for confirmation")
                    .isAuthenticated(true)
                    .build();


    }

    public TokenResponse refreshToken(String refreshToken) {

            TokenResponse tokenResponse =  jwtService.refreshToken(refreshToken);

            if(tokenResponse == null)
                throw new NoSuchElementException("No such token found");

            return tokenResponse;

    }

    private Long generateOtp()
    {
        return new Random().nextLong(100000, 199999);
    }

    public void sendOtpToUser(User user){

        Long otp = generateOtp();
        mailService.sendSimpleMail(user.getEmail(), "Confirmation Code", otp.toString());

        user.setCode(otp);
        user.setCodeExpiryDate(new Date(System.currentTimeMillis() + 86400000));
        user.setVerified(false);

        //متنساش تسيف بعد كدا الله يرضى عنك
        userRepository.save(user);
    }

    public void sendOtpToUser(String email){

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No such user found"));
        sendOtpToUser(user);
    }

    public void confirmEmail(ConfirmEmailDto confirmEmailDto)
    {
        User user = userRepository.findByEmail(confirmEmailDto.getEmail()).orElseThrow(() ->
                new NoSuchElementException("No such user found"));


        if(!user.getCode().equals(confirmEmailDto.getOtp()) ||
                user.getCodeExpiryDate().getTime() < System.currentTimeMillis())
            throw new CodeNotValidException("Code not valid");

        user.setVerified(true);
        user.setCode(0L);

        userRepository.save(user);
    }

    public void resetPassword(ResetPasswordDto resetPasswordDto)
    {
        User user = userRepository.findByEmail(resetPasswordDto.getEmail()).orElseThrow(() ->
                new NoSuchElementException("No such user found"));

        if((!Objects.equals(user.getCode(), resetPasswordDto.getOtp())) ||
                user.getCodeExpiryDate().getTime() < System.currentTimeMillis())
            throw new CodeNotValidException("Code not valid");


        if(!resetPasswordDto.getConfirmPassword().equals(resetPasswordDto.getPassword()))
            throw new PasswordsNotMatchedException("password and confirm password fields are not the same");

        user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        user.setCode(0L);

        userRepository.save(user);

    }


}
