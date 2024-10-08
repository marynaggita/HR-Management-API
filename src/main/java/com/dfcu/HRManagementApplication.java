package com.dfcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.dfcu.configs.Config;

@SpringBootApplication(scanBasePackages = "com.dfcu")
@Import(Config.class)
@EnableJpaRepositories(basePackages = {"com.dfcu.staffRegistration.repository", "com.dfcu.authentication.secretKey",
        "com.dfcu.authentication.user","com.dfcu.admin"})
@EntityScan(basePackages = "com.dfcu")
public class HRManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(HRManagementApplication.class, args);
    }
}