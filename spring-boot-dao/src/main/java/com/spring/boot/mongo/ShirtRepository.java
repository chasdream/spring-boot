/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.mongo;

import com.spring.boot.bean.Shirt;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * mongodb dao
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public interface ShirtRepository extends MongoRepository<Shirt, String> {

    Shirt findByModel(String model);

}
