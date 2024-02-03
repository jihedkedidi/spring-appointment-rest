package com.agil.admin;
//import com.agil.admin.config.SecurityConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
//@Import({SecurityConfig.class})
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
