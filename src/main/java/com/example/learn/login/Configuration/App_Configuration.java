package com.example.learn.login.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class App_Configuration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
