package com.creditcardanalyzer.mscreditanalyzer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.cards-issuing}")
    private String cardIssuingQueue;

    @Bean
    public Queue queueCardIssuing(){
        return new Queue(cardIssuingQueue, true);
    }
}
