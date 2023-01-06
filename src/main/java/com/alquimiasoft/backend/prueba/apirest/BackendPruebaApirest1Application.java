package com.alquimiasoft.backend.prueba.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendPruebaApirest1Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
		return application.sources(BackendPruebaApirest1Application.class);
		
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendPruebaApirest1Application.class, args);
	}

}
