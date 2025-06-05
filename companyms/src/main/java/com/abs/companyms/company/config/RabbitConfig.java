package com.abs.companyms.company.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Exchange name - where messages are sent
    public static final String COMPANY_EXCHANGE = "company.exchange";

    // Routing key - how messages are routed to queues
    public static final String COMPANY_DELETE_ROUTING_KEY = "company.delete";

    // Create the exchange (Producer only needs the exchange)
    @Bean
    public TopicExchange companyExchange() {
        return new TopicExchange(COMPANY_EXCHANGE);
    }
}