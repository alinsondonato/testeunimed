package br.com.alinson.testeunimed.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
	        .allowedOrigins("http://localhost:4200") // Substitua com a origem real do seu frontend
	        .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
