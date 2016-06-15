package com.demosite;

/*
	Simple resume creation service made with Spring boot. Template credit is given in index.hmtl source code
	Also uses iText api for editing the PDF at the end and various Apache api's for file manipulation
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class DemoWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebsiteApplication.class, args);
	}

}
