package com.neo.schedule;

import com.neo.rabbit.topic.TopicSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TopicSchedule {

    private static final Logger log = LoggerFactory.getLogger(TopicSchedule.class);

    @Autowired
    private TopicSender topicSender;

    @Scheduled(fixedRate = 1000)
    public void doStart(){
        topicSender.send();
        topicSender.send1();
        topicSender.send2();

        log.info("TopicSchedule is start!");
    }

}
