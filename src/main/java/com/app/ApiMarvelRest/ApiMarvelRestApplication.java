package com.app.ApiMarvelRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//Uso este decorador para que spring encuentre todas las clases del JAR y me permita usar @Autowired
@ComponentScan(basePackages = "com.app.TestJava")
//Tuve que incluirlo porque no me reconocia el RestController en el Swagger
@ComponentScan(basePackages = "com.app.ApiMarvelRest")
public class ApiMarvelRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMarvelRestApplication.class, args);
	}

}
