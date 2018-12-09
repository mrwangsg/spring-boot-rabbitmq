package com.neo.schedule;

import com.neo.model.User;
import com.neo.rabbit.delay.DelaySender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DelaySchedule {
    private static final Logger log = LoggerFactory.getLogger(DelaySchedule.class);

    @Autowired
    private DelaySender delaySender;

    @Autowired
    private User user;

    @Scheduled(fixedRate = 1000)
    public void doStart(){
        delaySender.send(user);
        log.info("DelaySchedule is start!");
    }
}
