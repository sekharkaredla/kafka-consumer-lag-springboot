package com.analyze.producer.producer.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class ProducerComponent {

    Logger logger = LoggerFactory.getLogger(ProducerComponent.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka_topic_name}")
    private String topicName;

    @Value("${wait_time_producer}")
    private int waitTime;

    private void sendEvent(String message) {
        String finalMessage = message + " -- " + new Date();
        logger.info("Sending Message : " + finalMessage);
        this.kafkaTemplate.send(topicName, finalMessage);
    }

    public void sendEventsWithTimeDelay(String message, int waitTime) {
        while (true) {
            try {
                Thread.sleep(waitTime);
                sendEvent(message);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @PostConstruct
    public void startPublishing() {
        this.sendEventsWithTimeDelay("Producer", waitTime);
    }

}
