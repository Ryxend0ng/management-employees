package com.amis.misa;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class StarterJava {
	public static void main(String[] args) {
        SpringApplication.run(StarterJava.class, args);
	}
}
