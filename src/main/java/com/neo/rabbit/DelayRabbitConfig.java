package com.neo.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelayRabbitConfig {
    private static final String Buffer_Exchange = "buffer_exchange";
    private static final String Process_Exchange = "process_exchange";

    private static final String Buffer_Queue = "buffer_queue";
    private static final String Process_Queue = "process_queue";

    private static final int Time_To_Live = 3000;

    @Bean
    DirectExchange bufferExchange() {
        return new DirectExchange(Buffer_Exchange);
    }

    @Bean
    DirectExchange processExchange() {
        return new DirectExchange(Process_Exchange);
    }

    /**
     * 创建缓冲队列，需要处理dead letter 后续去路
     *
     * @return
     */
    @Bean
    Queue bufferQueue() {
        return QueueBuilder.durable(Buffer_Queue)
                .withArgument("x-dead-letter-exchange", Process_Exchange)
                .withArgument("x-dead-letter-routing-key", Process_Queue)
                .withArgument("x-message-ttl", Time_To_Live)
                .build();
    }

    @Bean
    Queue processQueue() {
        return QueueBuilder.durable(Process_Queue)
                .build();
    }

    @Bean
    Binding bufferQueueBinding(Queue bufferQueue, DirectExchange bufferExchange) {
        return BindingBuilder.bind(bufferQueue).to(bufferExchange).with(Buffer_Queue);
    }

    @Bean
    Binding processQueueBinding(Queue processQueue, DirectExchange processExchange){
        return BindingBuilder.bind(processQueue).to(processExchange).with(Process_Queue);
    }

}
