package com.amis.misa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ViewResolver;



@SpringBootApplication
public class StartServer {
	public static void main(String[] args) {
		ApplicationContext contex= SpringApplication.run(StartServer.class, args);
	}
}