/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.service.impl;

import com.spring.boot.bean.Shirt;
import com.spring.boot.mongo.ShirtRepository;
import com.spring.boot.service.ShirtService;
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
@Service
public class ShirtServiceImpl implements ShirtService {

    @Autowired
    private ShirtRepository shirtRepository;

    @Override
    public Shirt findByModel(String model) {
        return shirtRepository.findByModel(model);
    }

    @Override
    public void saveShirt(Shirt shirt) {
        shirtRepository.save(shirt);
    }

    @Override
    public void deleteShirt(Shirt shirt) {
        shirtRepository.delete(shirt);
    }
}
