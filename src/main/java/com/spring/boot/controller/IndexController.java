/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.controller;

import com.spring.boot.model.IndexBean;
import com.spring.boot.model.Person;
import com.spring.boot.mongo.bean.Shirt;
import com.spring.boot.service.PersonService;
import com.spring.boot.service.ShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @RestController = @ResponseBody + @Controller
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
@Controller
public class IndexController {

    @Value("${index.name}")
    private String name;

    @Autowired
    private IndexBean indexBean;

    @Autowired
    private PersonService personService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ShirtService shirtService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {
        return name + ", you are welcome to SpringBoot!";
    }

    @RequestMapping(value = "/bean")
    @ResponseBody
    public String indexBean() {
        Person person = new Person();
        person.setName("black");
        person.setCount(100);
        int d = personService.addPerson(person);
        System.err.println("======>>> d: " + d);
        return "name: " + indexBean.getName()
                + "; age: " + indexBean.getAge()
                + "; sex: " + indexBean.getSex()
                + "; id: " + indexBean.getId();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public String findShirt(String model) {
        List<Shirt> shirt = mongoTemplate.findAll(Shirt.class);
        return shirt == null ? "shirt=null": shirt.toString();
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveShirt() {
        Shirt shirt = new Shirt();
        shirt.setId(3);
        shirt.setModel("model");
        shirt.setSize(30);
        shirtService.saveShirt(shirt);
        return "successfully";
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public String sendMessage(String name) {
        personService.sendMessage(name);
        return "successfully";
    }

}
