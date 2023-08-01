package com.app.auth.controller;

import com.app.auth.entity.User;
import com.app.auth.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
@Tag(
        name = "User registration",
        description = "Register users"
)
public class RegistrationController {

    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Object> addUser(@ModelAttribute("userForm") @Valid User user,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("registration");
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body("Passwords did not match");
        }

        if (!userService.saveUser(user)) {
            return ResponseEntity.badRequest().body(String.format("User with %s username already exists", user.getUsername()));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}

