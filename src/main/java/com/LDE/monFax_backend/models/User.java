package com.LDE.monFax_backend.models;

import com.LDE.monFax_backend.enumerations.UserType;
import jakarta.persistence.*;
import lombok.*;

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

     @Column(name = "last_login")
    private java.time.LocalDateTime lastLogin;

    public java.time.LocalDateTime getLastLogin() { return lastLogin; }

    public void setLastLogin(java.time.LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
}