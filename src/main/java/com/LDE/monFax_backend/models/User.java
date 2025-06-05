package com.LDE.monFax_backend.models;

import com.LDE.monFax_backend.enumerations.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private UserType role;
}