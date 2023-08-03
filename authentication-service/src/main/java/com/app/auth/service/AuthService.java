package com.app.auth.service;

import com.app.auth.entity.UserCredential;
import com.app.auth.exception.UserException;
import com.app.auth.repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private UserCredentialRepository repository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UserCredential saveUser(UserCredential credential) {
        if (repository.findByName(credential.getName()).isPresent() || repository.findByEmail(credential.getEmail()).isPresent()) {
            log.error("User already exist -> {}", credential);
            throw new UserException("User already exist with such email or username");
        }
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        log.debug("Saving user -> {}",credential);
        return repository.save(credential);
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
