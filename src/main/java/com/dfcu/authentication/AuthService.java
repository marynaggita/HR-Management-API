package com.dfcu.authentication;

import com.dfcu.authentication.user.User;
import com.dfcu.authentication.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void authenticateUser(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            log.info("Successful Authentication for user: {}", loginRequest.getUsername());
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", loginRequest.getUsername());
            throw new RuntimeException("Invalid username or password");
        }
    }

    // Method to authenticate using username and password
    public boolean authenticate(String username, String password) {
        // Fetch the user by username
        User user = userRepository.findByUsername(username);
        // Check if user exists and password matches
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            log.info("Authentication successful for user: " + username);
            return true; // Authentication successful
        }

        log.warn("User: " + username + "is not allowed perform this operation");
        return false; // Authentication failed
    }

    public User registerUser(User registrationRequest) {
        // Check if the username already exists
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            log.error("Registration failed: Username {} already taken", registrationRequest.getUsername());
            throw new RuntimeException("Username already taken");
        }

        // Encode the password before saving
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // Save the user
        log.info("User {} registered successfully", registrationRequest.getUsername());
        return userRepository.save(registrationRequest);
    }
}
