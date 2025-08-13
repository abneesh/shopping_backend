package com.sixammart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SixamMartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SixamMartApplication.class, args);
    }
}
