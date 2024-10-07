package com.dfcu.authentication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name; // This returns the role name, e.g., "ROLE_USER"
    }

}
