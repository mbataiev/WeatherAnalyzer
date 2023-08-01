package com.app.auth.controller;

import com.app.auth.entity.User;
import com.app.auth.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@Tag(
        name = "Admin controller",
        description = "Admin controller"
)
public class AdminController {
    private UserService userService;

    @GetMapping
    public List<User> userList() {
        return userService.findAll();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String > deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User successfully deleted");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<User>> gtUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.usergtList(userId));
    }
}
