package com.SpringProj.todo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "firstname is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "lastname is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "email is required")
    @Email
    @Column(unique = true)
    private String email;

    @NotNull(message = "password is required")
    private String password;


}
