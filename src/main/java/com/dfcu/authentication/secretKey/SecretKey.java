package com.dfcu.authentication.secretKey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "jwt_keys")
public class SecretKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getters and Setters
}