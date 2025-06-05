package com.abs.companyms.company.publisher;

import com.abs.companyms.company.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompanyEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public CompanyEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCompanyDeletedEvent(Long companyId) {
        try {
            // Send just the company ID as a Long
            rabbitTemplate.convertAndSend(
                    RabbitConfig.COMPANY_EXCHANGE,
                    RabbitConfig.COMPANY_DELETE_ROUTING_KEY,
                    companyId  // Just send the ID directly
            );

            System.out.println("Published company deleted event for ID: " + companyId);
        } catch (Exception e) {
            System.err.println("Failed to publish event: " + e.getMessage());
        }
    }
}