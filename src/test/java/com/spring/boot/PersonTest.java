/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot;

import com.alibaba.fastjson.JSON;
import com.spring.boot.model.Person;
import com.spring.boot.service.PersonService;
import com.spring.boot.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能描述
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testAddPerson() {
        Person person = new Person();
        person.setName("jack");
        person.setCount(101);
        int d = personService.addPerson(person);
        System.err.println("======>>> d: " + d);
    }

    @Test
    public void testFindName() {
        Person person = personService.findPerson("black");
        System.err.println("======>>> person: " + JSON.toJSONString(person));
    }

    @Test
    public void testSet() {
        redisUtils.set("name", "black");
        System.out.println("successfully!");
    }

    @Test
    public void testGetValue() {
        String value = redisUtils.getValue("name");
        System.err.println("name: " + value);
    }

}
