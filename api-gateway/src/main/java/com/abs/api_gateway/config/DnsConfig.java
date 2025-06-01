package com.abs.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class DnsConfig {

        @Bean
        public Void configureDns() {
            // Disable DNS caching
            java.security.Security.setProperty("networkaddress.cache.ttl", "0");
            java.security.Security.setProperty("networkaddress.cache.negative.ttl", "0");
            return null;
        }
    }

