package com.app;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class CustomPostProcessor implements MessagePostProcessor{

	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		
		// enrich message with our custom header
		message.getMessageProperties().getHeaders().put("foo", "bar");
		return message;
	}
}
