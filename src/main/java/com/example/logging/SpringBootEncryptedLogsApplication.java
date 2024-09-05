package com.example.logging;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
public class SpringBootEncryptedLogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEncryptedLogsApplication.class, args);
	}

}
