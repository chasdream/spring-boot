/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.service.impl;

import com.spring.boot.dao.PersonMapper;
import com.spring.boot.kafka.PersonProducer;
import com.spring.boot.model.Person;
import com.spring.boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonProducer personProducer;

    @Override
    public int addPerson(Person person) {
        return personMapper.insert(person);
    }

    @Override
    public Person findPerson(String name) {
        return personMapper.selectByName(name);
    }

    @Override
    public void sendMessage(String name) {
        Person person = personMapper.selectByName(name);
        personProducer.sendMessage(person);
    }
}
