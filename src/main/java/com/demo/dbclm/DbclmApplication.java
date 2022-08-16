package com.demo.dbclm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This the spring boot application to trigger DBCLM app for inserting and getting NACH (Nomenclature of Economic Activities) 
 * 
 * @author 7338877
 *
 */
@SpringBootApplication
@EnableSwagger2
public class DbclmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbclmApplication.class, args);
	}

}
