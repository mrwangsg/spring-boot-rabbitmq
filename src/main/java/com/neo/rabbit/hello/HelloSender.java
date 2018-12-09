package com.neo.rabbit.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {
	private static final Logger log = LoggerFactory.getLogger(HelloSender.class);

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		this.rabbitTemplate.convertAndSend("hello", "hello " + new Date());
		log.info("HelloSender is start!");
	}

}