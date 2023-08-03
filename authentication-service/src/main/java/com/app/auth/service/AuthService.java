package com.app.auth.service;

import com.app.auth.entity.UserCredential;
import com.app.auth.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserCredentialRepository repository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        if (repository.findByName(credential.getName()).isPresent() || repository.findByEmail(credential.getEmail()).isPresent()){
            throw new RuntimeException("User already exist");
        }

        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(UserCredential user) {
        return jwtService.generateToken(user.getName(), user.getEmail());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public UserCredential getUserByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User not exists with name -> %s", name))
        );
    }
}
