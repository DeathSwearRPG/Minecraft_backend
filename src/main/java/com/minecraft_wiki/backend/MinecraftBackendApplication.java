package com.minecraft_wiki.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MinecraftBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinecraftBackendApplication.class, args);
    }
}
