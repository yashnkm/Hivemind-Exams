package com.v1.online.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.v1.online.exam.repository",
									"com.v1.online.exam.config","com.v1.online.exam.controller"
										,"com.v1.online.exam.service","com.v1.online.exam.exception"})
@EntityScan(basePackages = {"com.v1.online.exam.entity"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
