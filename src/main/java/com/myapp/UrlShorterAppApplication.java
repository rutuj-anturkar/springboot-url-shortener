package com.myapp;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class UrlShorterAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(UrlShorterAppApplication.class, args);
		System.out.println("starting app");
	}

	@Bean
	public ModelMapper modelMapper(){
		// create model mapper instance
		// configure it for transferring matching and not null properties from src to dst object
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

}
