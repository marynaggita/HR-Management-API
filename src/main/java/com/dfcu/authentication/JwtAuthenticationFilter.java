package com.dfcu.authentication;

import com.dfcu.authentication.secretKey.SecretKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    private final SecretKeyService secretKeyService; // Service to retrieve secret key from DB

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, CustomUserDetailsService userDetailsService, SecretKeyService secretKeyService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.secretKeyService = secretKeyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Retrieve the JWT token from the request
        String token = getJwtFromRequest(request);

        // Fetch the secret key from the database via SecretKeyService
        String secretKey = secretKeyService.getSecretKey();

        // Validate the token and proceed if valid
        if (token != null ) {

            // Extract the username from the token using the secret key
            String username = jwtTokenUtil.getUsernameFromToken(token, secretKey);

            // Load the user details from the UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create an authentication object
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Set the authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }


    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}