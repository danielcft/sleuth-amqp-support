package com.app;

import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.instrument.messaging.HeaderBasedMessagingExtractor;
import org.springframework.cloud.sleuth.instrument.messaging.HeaderBasedMessagingInjector;
import org.springframework.cloud.sleuth.instrument.messaging.MessagingSpanTextMapExtractor;
import org.springframework.cloud.sleuth.instrument.messaging.MessagingSpanTextMapInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

	@Bean
	public MessagingSpanTextMapExtractor messagingSpanExtractor() {
		return new HeaderBasedMessagingExtractor();
	}

	@Bean
	public MessagingSpanTextMapInjector messagingSpanInjector(TraceKeys traceKeys) {
		return new HeaderBasedMessagingInjector(traceKeys);
}
	@Bean
	public DanielPostProcessor danielPostProcessor(){
		return new DanielPostProcessor();
	}
}
