package com.dfcu.authentication.secretKey;

import com.dfcu.authentication.secretKey.SecretKeyGenerator;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretKeyController {
    private final SecretKeyGenerator secretKeyGenerator;


    public SecretKeyController(SecretKeyGenerator secretKeyGenerator) {
        this.secretKeyGenerator = secretKeyGenerator;
    }

//    @PostMapping

}
