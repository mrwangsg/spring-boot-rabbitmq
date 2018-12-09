package com.neo.schedule;

import com.neo.rabbit.hello.HelloSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloSchedule {
    private static final Logger log = LoggerFactory.getLogger(HelloSchedule.class);

    @Autowired
    private HelloSender helloSender;

    @Scheduled(fixedRate = 1000)
    public void doStart(){
        this.helloSender.send();
        log.info("HelloShedule  is start!");
    }
}
