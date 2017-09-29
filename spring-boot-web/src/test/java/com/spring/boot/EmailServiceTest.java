/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot;

import com.spring.boot.email.EmailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

/**
 * 邮件发送单元测试
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public class EmailServiceTest extends SpringBootServiceApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        try {
            System.out.println("开始发送邮件!");
            emailService.sendEmail();
            System.out.println("邮件发送成功!");
        } catch (MessagingException e) {
            System.err.println("邮件发送异常! e: " + e.getMessage());
        }
    }

}
