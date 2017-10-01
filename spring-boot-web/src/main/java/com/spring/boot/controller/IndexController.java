/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.controller;

import com.spring.boot.bean.ApplicationBean;
import com.spring.boot.bean.IndexBean;
import com.spring.boot.bean.Person;
import com.spring.boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class IndexController {

    @Value("${index.name}")
    private String name;

    @Autowired
    private ApplicationBean applicationBean;

    @Autowired
    private IndexBean indexBean;

    @Autowired
    private PersonService personService;

    /**
     * controller中获取配置文件中的值
     *
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {
        return name + ", you are welcome to SpringBoot!";
    }

    /**
     * 实体类获取配置文件中的值
     *
     * @return
     */
    @RequestMapping(value = "/apply")
    @ResponseBody
    public String applicationBean() {
        return applicationBean.getName() + ", application.yml";
    }

    /**
     * 实体类获取自定义配置文件中的值
     *
     * @return
     */
    @RequestMapping(value = "/bean")
    @ResponseBody
    public String indexBean() {
        return "name: " + indexBean.getName()
                + "; age: " + indexBean.getAge()
                + "; sex: " + indexBean.getSex()
                + "; id: " + indexBean.getId();
    }

    /**
     * mysql insert
     * @return
     */
    @RequestMapping(value = "/savePerson")
    @ResponseBody
    public String savePerson() {
        Person person = new Person();
        person.setName("black");
        person.setCount(100);
        int d = personService.addPerson(person);
        return "successfully! " + d;
    }

    /**
     * kafka
     *
     * @return
     */
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @ResponseBody
    public String getKafkaMessage(String name) throws InterruptedException {
        personService.sendMessage(name);
        Thread.sleep(1000);
        return "send kafka message";
    }
}
