package com.project.fitwith.dto;

import com.project.fitwith.Entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestRegister {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "name is required")
    private String firstname;
    private String lastname;
    private UserRole role;
}
