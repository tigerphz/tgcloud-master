package com.tiger.tgcloud.gateway.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/22 10:24
 * @version: V1.0
 * @modified by:
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue queueMessage() {
        return new Queue("refreshRoutes");
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("amq.topic");
    }


    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("Gateway_RefreshRoutes");
    }
}
