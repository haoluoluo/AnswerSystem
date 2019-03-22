package com.luoluohao.answer_system.x.asconfig.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : S_ven
 * @create : 2018/11/8 10:45
 * @describe: 短信配置
 */

@Component
@ConfigurationProperties(value = "sms")
public class SmsConfig {

    private String getUri;

    private String sendSingleUri;

    private String sendBacthUri;

    private String sendTplUri;

    private String sendVoiceUri;

    private String bindUri;

    private String unbindUri;

    private String encoding;

    private String apikey;

    private String defaultCallbackUri;

    private String messageTemplateVerifyCode;

    private String messageTemplateFindCode;

    private String messageTemplateTransferNotice;

    public String getGetUri() {
        return getUri;
    }

    public void setGetUri(String getUri) {
        this.getUri = getUri;
    }

    public String getSendSingleUri() {
        return sendSingleUri;
    }

    public void setSendSingleUri(String sendSingleUri) {
        this.sendSingleUri = sendSingleUri;
    }

    public String getSendBacthUri() {
        return sendBacthUri;
    }

    public void setSendBacthUri(String sendBacthUri) {
        this.sendBacthUri = sendBacthUri;
    }

    public String getSendTplUri() {
        return sendTplUri;
    }

    public void setSendTplUri(String sendTplUri) {
        this.sendTplUri = sendTplUri;
    }

    public String getSendVoiceUri() {
        return sendVoiceUri;
    }

    public void setSendVoiceUri(String sendVoiceUri) {
        this.sendVoiceUri = sendVoiceUri;
    }

    public String getBindUri() {
        return bindUri;
    }

    public void setBindUri(String bindUri) {
        this.bindUri = bindUri;
    }

    public String getUnbindUri() {
        return unbindUri;
    }

    public void setUnbindUri(String unbindUri) {
        this.unbindUri = unbindUri;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getDefaultCallbackUri() {
        return defaultCallbackUri;
    }

    public void setDefaultCallbackUri(String defaultCallbackUri) {
        this.defaultCallbackUri = defaultCallbackUri;
    }

    public String getMessageTemplateVerifyCode() {
        return messageTemplateVerifyCode;
    }

    public void setMessageTemplateVerifyCode(String messageTemplateVerifyCode) {
        this.messageTemplateVerifyCode = messageTemplateVerifyCode;
    }

    public String getMessageTemplateTransferNotice() {
        return messageTemplateTransferNotice;
    }

    public void setMessageTemplateTransferNotice(String messageTemplateTransferNotice) {
        this.messageTemplateTransferNotice = messageTemplateTransferNotice;
    }

    public String getMessageTemplateFindCode() {
        return messageTemplateFindCode;
    }

    public void setMessageTemplateFindCode(String messageTemplateFindCode) {
        this.messageTemplateFindCode = messageTemplateFindCode;
    }
}
