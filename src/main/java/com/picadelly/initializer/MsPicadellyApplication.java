package com.picadelly.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.picadelly")
@EnableJpaRepositories(basePackages = "com.picadelly.repository")
@EntityScan("com.picadelly.domain")
public class MsPicadellyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPicadellyApplication.class, args);
	}

}
