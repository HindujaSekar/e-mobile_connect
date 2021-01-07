package com.telecom.mobileconnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EMobileconnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EMobileconnectionApplication.class, args);
	}

}
