package com.example.mymdb;

import com.example.mymdb.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableConfigurationProperties({
        FileStorageProperties.class
})
@SpringBootApplication
public class MymdbSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymdbSpringApplication.class, args);
    }
}
