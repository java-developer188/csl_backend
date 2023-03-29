package com.bootcamp.csl_manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootcamp.csl_manager.algorithms.FixtureAlgorithmFactory;
import com.bootcamp.csl_manager.common.FixtureAlgos;

@Configuration
public class AppConfig {
	
	@Bean
	public FixtureAlgorithmFactory getFixtureAlgoFactory() {
		
		return new FixtureAlgorithmFactory();
	}

}
