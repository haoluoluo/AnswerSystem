package com.luoluohao.answer_system.x.asconfig.yml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : S_ven
 * @create : 2018/11/8 15:23
 * @describe: Email配置
 */

@Component
@PropertySource("classpath:email.properties")
@ConfigurationProperties(value = "mail", prefix = "mail")
@ToString
@Getter
@Setter
public class Email {

    private String smtpHost;

    private Boolean smtpAuth;

    private Boolean smtpSslEnable;

    private Integer smtpTimeout;

    private String transportProtocol;

    private String fromUsername;

    private String fromAuthcode;

    private Boolean debug;



    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Boolean getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(Boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public Boolean getSmtpSslEnable() {
        return smtpSslEnable;
    }

    public void setSmtpSslEnable(Boolean smtpSslEnable) {
        this.smtpSslEnable = smtpSslEnable;
    }

    public Integer getSmtpTimeout() {
        return smtpTimeout;
    }

    public void setSmtpTimeout(Integer smtpTimeout) {
        this.smtpTimeout = smtpTimeout;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }

    public void setTransportProtocol(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getFromAuthcode() {
        return fromAuthcode;
    }

    public void setFromAuthcode(String fromAuthcode) {
        this.fromAuthcode = fromAuthcode;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }
}
