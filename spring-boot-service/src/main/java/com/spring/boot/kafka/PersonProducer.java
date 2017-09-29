/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.kafka;

import com.alibaba.fastjson.JSON;
import com.spring.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * kafka提供者
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
@Component
public class PersonProducer {

    private final static String TOPIC = "person-test";

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(Person person) {

        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(TOPIC, JSON.toJSONString(person));

        listenableFuture.addCallback(param->{
            System.out.println("kafka send message successfully!");
        }, error->{
            System.err.println("kafka send message failure!");
        });
    }
}
