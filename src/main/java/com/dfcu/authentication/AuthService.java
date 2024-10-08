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

import java.util.Base64;

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
    public boolean authenticate(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Decode the Base64 encoded username:password string
            String base64Credentials = authHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];

            User user = userRepository.findByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                log.info("Authentication successful for user: " + username);
                return true;
            }

            log.warn("User: " + username + "is not allowed perform this operation");
            return false; // Authentication failed
        }
        return false;
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
