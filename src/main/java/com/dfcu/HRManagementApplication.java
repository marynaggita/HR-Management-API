package com.dfcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.dfcu.configs.Config;

@SpringBootApplication
@Import(Config.class)
@EnableJpaRepositories("com.dfcu.repository")
@EntityScan("com.dfcu.models")
public class HRManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(HRManagementApplication.class, args);
    }
}