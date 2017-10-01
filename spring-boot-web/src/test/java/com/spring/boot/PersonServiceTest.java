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
import com.spring.boot.bean.Person;
import com.spring.boot.service.PersonService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
public class PersonServiceTest extends SpringBootServiceApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(PersonServiceTest.class);

    @Autowired
    private PersonService personService;

    @Test
    public void testAddPerson() {
        Person person = new Person();
        person.setName("jack");
        person.setCount(101);
        int d = personService.addPerson(person);
        logger.info("======>>> d: " + d);
    }

    @Test
    public void testFindName() {
        Person person = personService.findPerson("jack");
        logger.info("======>>> person: " + JSON.toJSONString(person));
    }
}
