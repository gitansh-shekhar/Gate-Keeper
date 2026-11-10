package com.gitansh.gatekeeper.api_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class ApiManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiManagementServiceApplication.class, args);
	}

}
