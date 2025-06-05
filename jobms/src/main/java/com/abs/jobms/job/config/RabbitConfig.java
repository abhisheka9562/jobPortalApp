package com.abs.jobms.job.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    // Exchange name - where messages are sent
    public static final String COMPANY_EXCHANGE = "company.exchange";

    // Queue names - where messages wait to be processed
    public static final String JOB_QUEUE = "job.company.delete.queue";
    public static final String REVIEW_QUEUE = "review.company.delete.queue";

    // Routing key - how messages are routed to queues
    public static final String COMPANY_DELETE_ROUTING_KEY = "company.delete";

    // Create the exchange
    @Bean
    public TopicExchange companyExchange() {
        return new TopicExchange(COMPANY_EXCHANGE);
    }

    // Create queues (only in job and review services)
    @Bean
    public Queue jobQueue() {
        return QueueBuilder.durable(JOB_QUEUE).build();
    }

    @Bean
    public Queue reviewQueue() {
        return QueueBuilder.durable(REVIEW_QUEUE).build();
    }

    // Bind queues to exchange with routing key
    @Bean
    public Binding jobBinding() {
        return BindingBuilder
                .bind(jobQueue())
                .to(companyExchange())
                .with(COMPANY_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding reviewBinding() {
        return BindingBuilder
                .bind(reviewQueue())
                .to(companyExchange())
                .with(COMPANY_DELETE_ROUTING_KEY);
    }
}