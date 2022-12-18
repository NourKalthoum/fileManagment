package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExternalFilePath implements WebMvcConfigurer {

    String ExternalFile = "file:///C:/Users/Mostafa/Desktop/app_4/demo/folder";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/folder/**").addResourceLocations(ExternalFile);
    }
}