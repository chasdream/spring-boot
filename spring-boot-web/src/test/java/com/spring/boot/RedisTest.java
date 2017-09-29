/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot;

import com.spring.boot.redis.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis单元测试
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public class RedisTest extends SpringBootServiceApplicationTests {

    @Autowired
    private RedisUtils redisUtils;

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
