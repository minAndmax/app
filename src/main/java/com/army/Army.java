package com.army;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.army.dao")
public class Army {

	public static void main(String[] args) {

		SpringApplication sapp = new SpringApplication(Army.class);
		sapp.run(args);

	}
}
