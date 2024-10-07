package com.dfcu.authentication.secretKey;

import com.dfcu.authentication.secretKey.SecretKey;
import com.dfcu.authentication.secretKey.SecretKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.util.Base64;

@Service
public class SecretKeyGenerator {
    @Autowired
    private final SecretKeyRepository secretKeyRepository;
        public SecretKeyGenerator(SecretKeyRepository secretKeyRepository) throws Exception {
            this.secretKeyRepository = secretKeyRepository;

            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
            keyGenerator.init(256); // for example, 256 bits
            javax.crypto.SecretKey secretKey = keyGenerator.generateKey();
            String newSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            SecretKey jwtKey = new SecretKey();
            jwtKey.setSecretKey(newSecretKey);
            secretKeyRepository.save(jwtKey);
        }

//    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
//
//    // Set the key size (for HmacSHA512, a size of 256 bits is common)
//    keyGenerator.init(256);
//
//    // Generate the secret key
//    SecretKey secretKey = keyGenerator.generateKey();
//
//    return secretKey;
}
