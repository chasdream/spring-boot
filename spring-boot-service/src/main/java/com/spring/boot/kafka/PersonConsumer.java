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
import com.alibaba.fastjson.JSONObject;
import com.spring.boot.bean.Person;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费者
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
public class PersonConsumer {

    private final static String TOPIC = "person-test";

    @KafkaListener(topics = {"person-test"})
    public void onMessage(ConsumerRecord<String, String> data) {

        System.out.println("==============start==============");

        System.out.println("接收kafka消息 topic:" + data.topic() + "; value:" + data.value());

        if (data.topic().equals(TOPIC)) {
            Person person = JSONObject.parseObject(data.value(), Person.class);
            System.out.println("接收kafka消息 person:" + JSON.toJSONString(person));
        }

        System.out.println("==============end==============");
    }
}
