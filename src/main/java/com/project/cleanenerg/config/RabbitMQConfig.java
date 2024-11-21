package com.project.cleanenerg.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.process.name}")
    private String queue;

    @Bean
    public Queue queue() {
        return new Queue(this.queue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}