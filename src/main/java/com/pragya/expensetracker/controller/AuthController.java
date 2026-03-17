package com.pragya.expensetracker.controller;

import com.pragya.expensetracker.entity.User;
import com.pragya.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");

        try {
            User user = userService.registerUser(username, email, password);

            return ResponseEntity.ok(
                    Map.of(
                            "message", "User registered successfully",
                            "username", user.getUsername()
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    // LOGIN endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        try {
            User user = userService.loginUser(username, password);

            // Generate fake JWT (we'll keep it simple for now)
            String fakeJwt = "VALID_JWT_TOKEN";

            return ResponseEntity.ok(
                    Map.of(
                            "token", fakeJwt,
                            "username", user.getUsername(),
                            "email", user.getEmail()
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(
                    Map.of("error", "Invalid username or password")
            );
        }
    }
}