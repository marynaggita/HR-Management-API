package com.dfcu.authentication;

import com.dfcu.authentication.secretKey.SecretKeyService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtTokenUtil{

    @Autowired
    private SecretKeyService secretKeyService;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours (in seconds)

    public String generateToken(String  username) {
        String secretKey = secretKeyService.getSecretKey();
        log.info("SecretKey: "+secretKey);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Retrieve username from JWT token
    public String getUsernameFromToken(String token, String secretKey) {
        return getClaimsFromToken(token, secretKey).getSubject();
    }

    // Retrieve expiration date from JWT token
    public Date getExpirationDateFromToken(String token, String secretKey) {
        return getClaimsFromToken(token, secretKey).getExpiration();
    }

    //Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        String secretKey = secretKeyService.getSecretKey();

        final String username = getUsernameFromToken(token, secretKey);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, secretKey));
    }

    // Check if the token has expired
    public boolean isTokenExpired(String token, String secretKey) {
        final Date expiration = getExpirationDateFromToken(token, secretKey);
        return expiration.before(new Date());
    }


    // Method to retrieve claims from the token
    private Claims getClaimsFromToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


}
