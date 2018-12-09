package com.neo.schedule;

import com.neo.rabbit.fanout.FanoutSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class FanoutSchedule {
    private static final Logger log = LoggerFactory.getLogger(FanoutSchedule.class);

    @Autowired
    private FanoutSender fanoutSender;

    @Scheduled(fixedRate = 1000)
    public void doStart(){
        this.fanoutSender.send();
        log.info("FanoutSchdule is start!");
    }
}
