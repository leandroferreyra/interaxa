package com.interaxa.challenge.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.interaxa.challenge.controllers"))
				.paths(paths())
				.build()
				.apiInfo(apiInfo());
	}
	
	private Predicate<String> paths() {
		return or(regex("/personas/.*"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
					"My REST API INTERAXA", 
					"Description API", 
					"API VERSION", 
					"Terms of service", 
					new Contact("John Doe", "url example", "email"), 
					"License of API", 
					"API License URL", 
					Collections.emptyList());
	}
	

}
