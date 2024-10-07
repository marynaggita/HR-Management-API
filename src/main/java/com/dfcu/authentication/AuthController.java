package com.dfcu.authentication;

import com.dfcu.authentication.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
@Slf4j
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        log.info("Login Request: "+loginRequest);
        try {
            // Authenticate user using AuthService
            authService.authenticateUser(loginRequest);
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User registrationRequest) {
        try {
            User newUser = authService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully: " + newUser.getUsername(), true));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error: " + e.getMessage(), false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: An unexpected error occurred.", false));
        }

    }
}