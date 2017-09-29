/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @Repository、@Service、@Controller和@Component将类标识为Bean
 *
 * @Repository:(Spring2.0版本开始引入)用于将数据访问层(DAO层)的类标识为SpringBean，并且它还能将所标注的类中抛出的数据访问异常封装为Spring的数据访问异常类型;
 *
 * Spring2.5版本在@Repository的基础上增加了功能类似的额外三个注解：@Component、@Service、@Controller，它们分别用于软件系统的不同层次：
 * @Component:是一个泛化的概念，仅仅表示一个组件(Bean)，可以作用在任何层次;
 * @Service:通常作用在业务层，但是目前该功能与@Component相同;
 * @Controller:通常作用在控制层，但是目前该功能与@Component相同;
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
@Repository
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, 30, TimeUnit.SECONDS);
    }

    public String getValue(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

}
