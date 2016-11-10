package com.app;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class DanielPostProcessor implements MessagePostProcessor{

	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		message.getMessageProperties().getHeaders().put("danielCustomHeader", "someHeaderValue");
		return message;
	}
}
