package com.abs.jobms.job.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Exchange name - must match producer
    public static final String COMPANY_EXCHANGE = "company.exchange";

    // Queue name - specific to Job service
    public static final String JOB_QUEUE = "job.company.delete.queue";

    // Routing key - must match producer
    public static final String COMPANY_DELETE_ROUTING_KEY = "company.delete";

    // Create the exchange (Consumer also needs exchange reference)
    @Bean
    public TopicExchange companyExchange() {
        return new TopicExchange(COMPANY_EXCHANGE);
    }

    // Create queue for Job service
    @Bean
    public Queue jobQueue() {
        return QueueBuilder.durable(JOB_QUEUE).build();
    }

    // Bind Job queue to exchange with routing key
    @Bean
    public Binding jobBinding() {
        return BindingBuilder
                .bind(jobQueue())
                .to(companyExchange())
                .with(COMPANY_DELETE_ROUTING_KEY);
    }
}