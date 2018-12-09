package com.neo.schedule;

import com.neo.model.User;
import com.neo.rabbit.object.ObjectSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ObjectSchedule {
    private static final Logger log = LoggerFactory.getLogger(ObjectSchedule.class);

    @Autowired
    private ObjectSender objectSender;

    @Autowired
    private User user;

    @Scheduled(fixedRate = 1000)
    public void doStart(){
        log.info("========================");
        this.objectSender.send(this.user);
        log.info("ObjectSchedule is start!");
    }

}
