package com.example.mung;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mung.mapper")
public class MungApplication {

	public static void main(String[] args) {
		SpringApplication.run(MungApplication.class, args);
	}

}
