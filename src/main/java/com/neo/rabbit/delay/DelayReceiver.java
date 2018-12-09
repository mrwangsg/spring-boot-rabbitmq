package com.neo.rabbit.delay;

import com.neo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "process_queue")
public class DelayReceiver {
    private static final Logger log = LoggerFactory.getLogger(DelayReceiver.class);

    @RabbitHandler
    public void process(User user) {
        log.info("DelayReceiver object : " + user + ";   name: " + user.getName());

    }
}
