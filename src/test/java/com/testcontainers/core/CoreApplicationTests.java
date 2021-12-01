package com.testcontainers.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
class CoreApplicationTests {

	static final MySQLContainer DATABASE = new MySQLContainer();

	@Test
	void contextLoads() {
	}

	static {
		DATABASE.start();
	}

	@DynamicPropertySource
	static void databaseProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", DATABASE::getJdbcUrl);
		registry.add("spring.datasource.username", DATABASE::getUsername);
		registry.add("spring.datasource.password", DATABASE::getPassword);
		registry.add("flyway.user", () -> "root");
		registry.add("flyway.password", DATABASE::getPassword);
	}
}
