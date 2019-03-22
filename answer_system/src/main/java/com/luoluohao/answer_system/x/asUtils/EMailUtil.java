package com.luoluohao.answer_system.x.asUtils;


import com.luoluohao.answer_system.util.StringUtil;
import com.luoluohao.answer_system.x.asconfig.yml.Email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * 电子邮件收发工具
 *
 * @Filename: com.abcdpower.x.iwllutil.EMailUtil.java
 * @Type: EMailUtil
 * @Author: macbook[weide<weide001                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               @                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               gmail.com>]
 * @Created: 2018/9/22 下午4:13
 * @Version: iwill.zt-service V1.0.0
 */
public class EMailUtil {

    /**
     * @Description 发送邮件（文本）
     * @Param [config, recipients, title, content]
     * @Return boolean
     */
    public static boolean Send(Email email, String recipients, String title, String content) throws AddressException, javax.mail.MessagingException {
        if (StringUtil.IsEmpty(recipients)) {
            return false;
        }
        if(email == null){
            System.out.println("====================================");
        }
        System.out.println(email);
        // 设置会话属性
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", email.getTransportProtocol());    // 连接协议
        properties.put("mail.smtp.host", email.getSmtpHost());                        // 主机名称
//        properties.put("mail.smtp.port", config.get("mail.smtp.port"));						// 开放端口
        properties.put("mail.smtp.auth", email.getSmtpAuth());                        // 授权验证？
        properties.put("mail.smtp.ssl.enable", email.getSmtpSslEnable());            // 安全连接？
        properties.put("mail.smtp.timeout", email.getSmtpTimeout());                // 等待超时
        properties.put("mail.debug", email.getDebug());                                // 调试模式？
        synchronized (EMailUtil.class) {
            // 获取会话对象
            Session session = Session.getInstance(properties);
            session.setDebug(email.getDebug());
            // 创建信件对象
            MimeMessage message = new MimeMessage(session);
            // 设置发件人邮箱
            message.setFrom(new InternetAddress(email.getFromUsername()));
            // 设置收件人邮箱（加载收件人地址）
            String[] tos = recipients.split(",|;");
            InternetAddress[] sendTos = new InternetAddress[tos.length];
            for (int i = 0; i < tos.length; i++) sendTos[i] = new InternetAddress(tos[i]);
            message.setRecipients(Message.RecipientType.TO, sendTos);
            //163邮件服务器下，需要要设置在发送给收信人之前给自己（发送方）抄送一份，不然会被当成垃圾邮件，报554错
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(email.getFromUsername()));
            // 设置邮件主题
            message.setSubject(title);
            // 设置邮件内容
            // 发纯文本邮件
            //message.setText("测试通过QQ邮件服务器发送邮件。");
            // 带附件的邮件（向multipart对象中添加邮件的各个部分内容，包括文本内容和附件）
            Multipart multipart = new MimeMultipart();
            // 设置文本内容
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(content);
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 获取邮差对象
            Transport transport = session.getTransport();
            // 连接发送邮箱
            transport.connect(email.getFromUsername(), email.getFromAuthcode());
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            // 关闭通道
            transport.close();
        }
        return true;
    }
}
