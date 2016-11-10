package com.app;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.instrument.messaging.HeaderBasedMessagingExtractor;
import org.springframework.cloud.sleuth.instrument.messaging.HeaderBasedMessagingInjector;
import org.springframework.cloud.sleuth.instrument.messaging.MessagingSpanTextMapExtractor;
import org.springframework.cloud.sleuth.instrument.messaging.MessagingSpanTextMapInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    final static String queueName = "spring-boot";

	@Bean
	public MessagingSpanTextMapExtractor messagingSpanExtractor() {
		return new HeaderBasedMessagingExtractor();
	}

	@Bean
	public MessagingSpanTextMapInjector messagingSpanInjector(TraceKeys traceKeys) {
		return new HeaderBasedMessagingInjector(traceKeys);
}
	@Bean
	public CustomPostProcessor danielPostProcessor(){
		return new CustomPostProcessor();
	}

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
}
