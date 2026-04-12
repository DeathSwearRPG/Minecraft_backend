package com.minecraft_wiki.backend;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class MinecraftBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinecraftBackendApplication.class, args);
	}

}
