/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
@Component
public class ScheduledTask {

    @Scheduled(cron = "0 03 17 * * ? ")
    public void scheduled() {
        System.out.println(">>> start scheduled task >>>");
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        System.out.println("currentTime: " + format.format(new Date()));
    }
}
