package com.ard333.springbootwebfluxjjwt.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 *
 * @author ard333
 */
@Configuration
@EnableWebFlux
public class CORSFilter implements WebFluxConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4200","http://www.chevere.ga","https://www.chevere.ga").allowedMethods("GET", "POST", "PUT","DELETE").allowedHeaders("*");
	}
}