/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.email;

import javax.mail.MessagingException;

/**
 * 集成jms
 *
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public interface EmailService {

    /**
     * 发送邮件
     *
     * @return
     */
    void sendEmail() throws MessagingException;
}
