package com.example.smittirechangeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SmitTireChangeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmitTireChangeAppApplication.class, args);
	}

}
