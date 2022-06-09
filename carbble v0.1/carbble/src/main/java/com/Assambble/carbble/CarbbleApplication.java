package com.Assambble.carbble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class CarbbleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarbbleApplication.class, args);
	}

}
