package com.example.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDbApplication {

	public static void main(String[] args) {
		SpringApplication.from(DbApplication::main).with(TestDbApplication.class).run(args);
	}

}
