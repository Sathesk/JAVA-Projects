package com.microservice.e_com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //to start the server
public class EComEurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(EComEurekaServer.class, args);
	}

}
