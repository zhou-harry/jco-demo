package com.harry.jcodemo;

import com.harry.jcodemo.jco.JcoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JcoProperties.class)
public class JcoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcoDemoApplication.class, args);
	}

}
