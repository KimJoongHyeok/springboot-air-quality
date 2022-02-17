package com.example.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AirQualityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirQualityServiceApplication.class, args);
	}

}
