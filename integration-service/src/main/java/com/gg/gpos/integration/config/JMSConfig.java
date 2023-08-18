package com.gg.gpos.integration.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Configuration
@EnableJms
public class JMSConfig {
	public static final String QUEUE_SEND_FILE = "queue-send-file";
	
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(QUEUE_SEND_FILE);
    }
}
