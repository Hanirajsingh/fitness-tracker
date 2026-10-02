package com.project.fitwith.Service;

import com.project.fitwith.Entity.User;
import com.project.fitwith.Entity.UserRole;
import com.project.fitwith.Repository.UserRepository;
import com.project.fitwith.dto.LoginRequest;
import com.project.fitwith.dto.RequestRegister;
import com.project.fitwith.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service

public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    public UserResponse register(RequestRegister request){
        UserRole role = request.getRole() != null ? request.getRole():UserRole.USER;
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(role)
                        .build();
         User savedUser =  userRepository.save(user);
         return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser){
        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstname(savedUser.getFirstname());
        response.setLastname(savedUser.getLastname());
        response.setCreated_at(savedUser.getCreated_at());
        response.setUpdated_at(savedUser.getUpdated_at());

        return response;
    }
    public User authenticate (LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null)
            throw new RuntimeException("Invalid credentials");

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw  new RuntimeException("Invalid Credentials");
        }
        return user;
    }
}
