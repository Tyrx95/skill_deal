package com.skilldealteam.skilldeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,HibernateJpaAutoConfiguration.class })

public class SkilldealApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkilldealApplication.class, args);
	}
}
