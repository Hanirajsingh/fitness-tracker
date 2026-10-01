package com.project.fitwith.Controller;

import com.project.fitwith.Entity.User;
import com.project.fitwith.Repository.UserRepository;
import com.project.fitwith.Security.JwtUtils;
import com.project.fitwith.Service.UserService;
import com.project.fitwith.dto.LoginRequest;
import com.project.fitwith.dto.LoginResponse;
import com.project.fitwith.dto.RequestRegister;
import com.project.fitwith.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
//@RequiredArgsConstructor //generate only constructor which is required and final
public class AuthController {

    private final UserService userService;

    private JwtUtils jwtUtils;

    public AuthController (UserService userService,
                           JwtUtils jwtUtils){

        this.userService =userService;
        this.jwtUtils=jwtUtils;
    }

    @PostMapping("/register")
    public UserResponse Register (@RequestBody RequestRegister requestRegister){
        return  userService.register(requestRegister);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest){

        try{
            User user = userService.authenticate(loginRequest);

            String token = jwtUtils.generateToken(user.getId(),
                    user.getRole().name());

            return ResponseEntity.ok(new LoginResponse(token,
                    userService.mapToResponse(user)));

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
