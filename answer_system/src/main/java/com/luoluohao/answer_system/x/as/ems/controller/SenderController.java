package com.luoluohao.answer_system.x.as.ems.controller;

import com.luoluohao.answer_system.x.asUtils.EMailUtil;
import com.luoluohao.answer_system.x.asconfig.yml.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * 邮件发送控制
 *
 * @Type: SenderController
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/21 下午1:57
 * @Version: iwill.zt-service V1.0.0
 */
@RestController(value = "emsSenderController")
@EnableAutoConfiguration
@RequestMapping("/emssender")
public class SenderController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Email email;
    /**
     * @Description 发送邮件（纯文本）
     *
     * @Param [recipients, text]
     * @Return java.lang.Boolean
     */
    public Boolean sendMail(String recipients, String title, String text) {
        try {
            return EMailUtil.Send(email,recipients,title,text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
