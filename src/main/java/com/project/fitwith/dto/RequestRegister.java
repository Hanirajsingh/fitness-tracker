package com.project.fitwith.dto;

import com.project.fitwith.Entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestRegister {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private UserRole role;
}
