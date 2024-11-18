package com.project.cleanenerg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CleanenergApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanenergApplication.class, args);
	}

}
