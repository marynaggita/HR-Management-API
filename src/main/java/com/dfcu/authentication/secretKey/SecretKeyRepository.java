package com.dfcu.authentication.secretKey;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SecretKeyRepository extends JpaRepository<SecretKey, Long> {

    // Fetch the latest secret key (adjust query according to your database schema)
    SecretKey findFirstByOrderByIdAsc();
}