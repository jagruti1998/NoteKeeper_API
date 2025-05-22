package com.notekeeperapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NotekeeperapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotekeeperapiApplication.class, args);
	}

}
