package com.neo.rabbit.object;

import com.neo.model.User;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {
    private static final Logger log = LoggerFactory.getLogger(ObjectReceiver.class);

    @RabbitHandler
    public void process(User user, Channel channel, Message message){
        log.info("ObjectReceiver object : " + user + ";   name: " + user.getName());

        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("ObjectReceiver success. " + message.getMessageProperties().getDeliveryTag());
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
                log.info("ObjectReceiver fail. basicNack()");
            }
            log.info("ObjectReceiver fail. " + message.getMessageProperties().getDeliveryTag());
        }
    }

}
