package com.dfcu.authentication.secretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretKeyService {

    @Autowired
    private SecretKeyRepository secretKeyRepository;

    public String getSecretKey() {
        SecretKey secretKey = secretKeyRepository.findFirstByOrderByIdAsc();
        return secretKey != null ? secretKey.getSecretKey() : null; // Handle case where no key is found
    }
}