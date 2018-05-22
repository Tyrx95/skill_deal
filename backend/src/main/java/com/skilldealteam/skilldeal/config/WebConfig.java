package com.skilldealteam.skilldeal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*registry.addMapping("/**")
                .allowedOrigins("http://localhost",
                                "http://127.0.0.1",
                                "http://skilldeal.bss.design/",
                                "http://92.36.186.173",
                                "http://92.36.186.173:*",
                                "https://92.36.186.173",
                                "https://92.36.186.173:*",
                                "http://31.13.112.129",
                                "http://31.13.112.129:*",
                                "https://31.13.112.129",
                                "https://31.13.112.129:*"

                )
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");*/
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedOrigins("*")
                .allowedHeaders("*");
    }
}
