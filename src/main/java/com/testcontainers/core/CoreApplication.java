package com.testcontainers.core;

import org.junit.ClassRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testcontainers.containers.GenericContainer;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@ClassRule
	public static GenericContainer simpleWebServer = new GenericContainer("alpine:3.2")
			.withExposedPorts(80)
			.withCommand("/bin/sh", "-c", "while true; do echo "+"\"HTTP/1.1 200 OK\n\nHello World!\" | nc -l -p 80; done");

}
