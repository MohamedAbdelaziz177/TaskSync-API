package com.SpringProj.todo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "role cannot be null")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
