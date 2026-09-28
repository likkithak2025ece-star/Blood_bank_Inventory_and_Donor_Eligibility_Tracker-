package com.blood.bank.controller;

import com.blood.bank.entity.User;
import com.blood.bank.entity.UserResponse;
import com.blood.bank.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        try {
            userService.registerUser(user);
            return "User registered successfully";

        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existingUser =
                userService.findByUsername(user.getUsername());

        if (existingUser != null &&
            userService.passwordMatches(
                    user.getPassword(),
                    existingUser.getPassword())) {

            return "Login successful";
        }

        return "Invalid username or password";
    }

    // Admin access
    @GetMapping("/all")
public java.util.List<UserResponse> getAllUsers() {

    return userService.getAllUsers()
            .stream()
            .map(user -> new UserResponse(
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole()
            ))
            .toList();
}

    // Change password
    @PostMapping("/change-password")
    public String changePassword(
            @RequestBody Map<String, String> data) {

        String username = data.get("username");

        String oldPassword = data.get("oldPassword");

        String newPassword = data.get("newPassword");

        if (username == null ||
            oldPassword == null ||
            newPassword == null) {

            return "All fields are required";
        }

        if (newPassword.length() < 4) {

            return "New password must contain at least 4 characters";
        }

        boolean changed =
                userService.changePassword(
                        username,
                        oldPassword,
                        newPassword
                );

        if (changed) {

            return "Password changed successfully";

        } else {

            return "Current password is incorrect";
        }
    }
}