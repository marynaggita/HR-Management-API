package com.dfcu.authentication.user;

import com.dfcu.authentication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
