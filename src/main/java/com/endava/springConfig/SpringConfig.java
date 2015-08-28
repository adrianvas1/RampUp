package com.endava.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.endava.config.MessagesConfiguration;
import com.endava.resource.CRUDResource;

@Configuration
public class SpringConfig {

	@Bean
	public CRUDResource helloResource() {
		return new CRUDResource(messagesConfiguration());
	}
	
	@Bean 
	public MessagesConfiguration messagesConfiguration() {
		return new MessagesConfiguration();
	}
	
}
