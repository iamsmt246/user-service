package com.realtimeStockPortfolio.userService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "username cannot be blank")
    String username;

    @NotBlank(message = "password cannot be blank")
    String password;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email format")
    String email;
}
