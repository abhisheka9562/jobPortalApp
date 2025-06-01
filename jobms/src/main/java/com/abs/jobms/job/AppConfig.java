package com.abs.jobms.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced  // Enables client-side load balancing with service discovery
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
