package com.dfcu.authentication.user;

import com.dfcu.authentication.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // Adjust the table name to match your database
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true) // Ensure username is unique and not null
    private String username;

    @Column(nullable = false) // Ensure password is not null
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // This is the join table name
            joinColumns = @JoinColumn(name = "user_id"), // Column name in the user_roles table
            inverseJoinColumns = @JoinColumn(name = "role_id") // Column name in the role table
    )
    private Collection<Role> roles; // Assuming you have a Role entity

    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles; // Assuming Role implements GrantedAuthority
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}