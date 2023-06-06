package com.example.ab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class abApplication {

	public static void main(String[] args) {
		SpringApplication.run(abApplication.class, args);
		LinkController.DodajPracownikow();
	}

}
