package com.cg.eshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cg.eshopping")
public class EShoppingZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShoppingZoneApplication.class, args);
	}

}
