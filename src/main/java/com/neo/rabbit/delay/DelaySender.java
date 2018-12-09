package com.neo.rabbit.delay;

import com.neo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelaySender{
    private static final Logger log = LoggerFactory.getLogger(DelaySender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        this.rabbitTemplate.convertAndSend("buffer_exchange", "buffer_queue", user);
    }

}
