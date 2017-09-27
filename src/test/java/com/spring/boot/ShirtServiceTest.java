/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot;

import com.spring.boot.mongo.bean.Shirt;
import com.spring.boot.service.ShirtService;
import org.junit.Test;
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
public class ShirtServiceTest extends ApplicationTests {

    @Autowired
    private ShirtService shirtService;

    @Test
    public void testFindByModel() {
        Shirt shirtF = shirtService.findByModel("shirt");
        System.err.println("shirt: " + shirtF);
    }

    @Test
    public void testSave() {
        Shirt shirt = new Shirt();
        shirt.setId(4);
        shirt.setModel("shirt");
        shirt.setSize(31);
        shirtService.saveShirt(shirt);
    }

    @Test
    public void testDelete() {
        Shirt shirt = new Shirt();
        shirt.setId(4);
        shirtService.deleteShirt(shirt);
    }
}
