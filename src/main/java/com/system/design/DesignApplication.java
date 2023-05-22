package com.system.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
public class DesignApplication {


	public static void main(String[] args) {
		SpringApplication.run(DesignApplication.class, args);

	}

}
