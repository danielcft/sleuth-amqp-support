/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {
    final static String queueName = "spring-boot";

	@Autowired
	private Tracer tracer;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private ConfigurableApplicationContext context;
    
    @Autowired
	private SpanAccessor spanAccessor; 

    @Autowired
    private DanielPostProcessor dpp;
    
    @Override
    public void run(String... args) throws Exception {
        //System.out.println("Sending message...");
        rabbitTemplate.setBeforePublishPostProcessors(dpp);
        
        rabbitTemplate.convertAndSend(queueName, "Hello from RabbitMQ!");
        
        //context.close();
    }

}
