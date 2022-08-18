package com.analyze.consume.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConsumerService {

    Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Value("${wait_time_consumer}")
    private int waitTime;

    @KafkaListener(topics = "${kafka_topic_name}", groupId = "${kafka_group_id}")
    public void receive(String message) {
        try {
            Thread.sleep(waitTime);
            String finalMessage = message + " -- " + new Date();
            logger.info("Receiving Message : " + finalMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
