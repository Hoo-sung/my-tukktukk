package com.tukktukk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TukktukkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TukktukkApplication.class, args);
	}

}
