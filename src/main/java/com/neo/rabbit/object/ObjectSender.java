package com.neo.rabbit.object;

import com.neo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {
    private static final Logger log = LoggerFactory.getLogger(ObjectSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {

        // 进行确认机制
        // 进行回调机制
        // 每个RabbitTemplate 只能有一个Callback回调
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);

        this.rabbitTemplate.convertAndSend("object", user);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("ObjectSender return success" + message.toString() + "===" + i + "===" + s1 + "===" + s2);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String msg) {
        if (!ack) {
            log.info("ObjectSender 消息发送 失败！ " + msg + correlationData.toString());
        } else {
            log.info("ObjectSender 消息发送 成功！ ");
        }
    }
}