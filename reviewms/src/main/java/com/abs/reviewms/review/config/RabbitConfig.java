package com.abs.reviewms.review.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Exchange name - must match producer
    public static final String COMPANY_EXCHANGE = "company.exchange";

    // Queue name - specific to Review service
    public static final String REVIEW_QUEUE = "review.company.delete.queue";

    // Routing key - must match producer
    public static final String COMPANY_DELETE_ROUTING_KEY = "company.delete";

    // Create the exchange (Consumer also needs exchange reference)
    @Bean
    public TopicExchange companyExchange() {
        return new TopicExchange(COMPANY_EXCHANGE);
    }

    // Create queue for Review service
    @Bean
    public Queue reviewQueue() {
        return QueueBuilder.durable(REVIEW_QUEUE).build();
    }

    // Bind Review queue to exchange with routing key
    @Bean
    public Binding reviewBinding() {
        return BindingBuilder
                .bind(reviewQueue())
                .to(companyExchange())
                .with(COMPANY_DELETE_ROUTING_KEY);
    }
}