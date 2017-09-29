/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.spring.boot.email.impl;

import com.spring.boot.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 邮件发送
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl sender;

    @Override
    public void sendEmail() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        //multipart模式
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setTo(new String[]{"example@163.com"});//收件人
        helper.setFrom("example@qq.com");//发件人
        helper.setSubject("SpringBoot测试邮件发送");

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("<a href=\"www.baidu.com\">测试邮件"
                + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "</a>");

        //cid默认写法，imageId用于标识内嵌图片
        sBuilder.append("图片：<img src=\"cid:imageId\"/>");

        FileSystemResource inlineImg = new FileSystemResource(new File("F:\\0018.jpg"));
        helper.addInline("imageId", inlineImg);//内嵌图片，设置imageId

        helper.setText(sBuilder.toString(), true);//开启html

        //设置附件
        FileSystemResource attachmentImg = new FileSystemResource(new File("F:\\0001.jpg"));
        helper.addAttachment("attachment.jpg", attachmentImg);

//        sender.send(mimeMessage);//发送邮件
    }
}
