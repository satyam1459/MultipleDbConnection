package com.example.MultipleDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.MultipleDb.db1.entities", "com.example.MultipleDb.db2.entities"})
@EnableJpaRepositories(basePackages = {"com.example.MultipleDb.db1.repo", "com.example.MultipleDb.db2.repo"})
@ComponentScan(basePackages = {"com.example.MultipleDb"})
public class MultipleDbApplication {
	public static void main(String[] args) {
		SpringApplication.run(MultipleDbApplication.class, args);
	}
}