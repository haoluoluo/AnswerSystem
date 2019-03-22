package com.luoluohao.answer_system.x.as.sms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luoluohao.answer_system.util.ObjectUtil;
import com.luoluohao.answer_system.util.StringUtil;
import com.luoluohao.answer_system.x.asUtils.YunPianUtil;
import com.luoluohao.answer_system.x.asconfig.yml.SmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送控制
 *
 * @Filename: com.abcdpower.x.iwill.sms.controller.SenderController.java
 * @Type: SenderController
 * @Created: 2018/9/21 下午1:49
 * @Version: iwill.zt-service V1.0.0
 */
@RestController(value = "smsSenderController")
@EnableAutoConfiguration
@RequestMapping("/sms/sender")
public class SenderController {

    @Autowired
    private SmsConfig smsConfig;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description 发送短信
     * @Param [tels, message, options]
     * @Return java.lang.Boolean
     */
    public Boolean sendMessage(String tels, String message, HashMap<String, Object> options) {
        if (StringUtil.IsEmpty(tels) || StringUtil.IsEmpty(message)) return false;

        Map<String, String> params = new HashMap<>();
        params.put("apikey", smsConfig.getApikey());//APIKEY
        params.put("text", message);
        params.put("mobile", tels);
        String callback = smsConfig.getDefaultCallbackUri(); //sms.callback.url.default
        if (null != options) {
            if (null != options.get("extend"))
                params.put("extend", ObjectUtil.ToString(options.get("extend")));
            if (null != options.get("uid"))
                params.put("uid", ObjectUtil.ToString(options.get("uid")));
            callback = null == options.get("callback") ? callback : ObjectUtil.ToString(options.get("callback"));
        }
        params.put("callback_url", callback);
        //sms.url.send.single
        //sms.url.send.bacth
        //sms.encoding
        String response = YunPianUtil.Send(
                0 >= tels.indexOf(",") ? smsConfig.getSendSingleUri() : smsConfig.getSendBacthUri(),
                params,
                smsConfig.getEncoding());

        try {
            JSONObject json = JSON.parseObject(response);
            int code = json.getInteger("code");
            String msg = json.getString("msg");
            if (code == 0) {
                logger.info("短信下发成功-----------------------------------------");
                logger.info("短信下发code:" + code);
                logger.info("短信下发msg:" + json.getString("msg"));
                logger.info("短信下发mobile:" + json.getString("mobile"));
                logger.info("----------------------------------------------------");
                return true;
            } else {

                logger.error("短信下发失败-----------------------------------------");
                logger.error("短信下发http_status_code:" + json.getInteger("http_status_code"));
                logger.error("短信下发code:" + code);
                logger.error("短信下发msg:" + msg);
                logger.error("短信下发detail:" + json.getString("detail"));
                logger.error("----------------------------------------------------");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("短信下发失败-----------------------------------------");
            logger.error(e.getMessage());
            logger.error("----------------------------------------------------");
            return false;
        }

    }

    public Boolean sendTransferNotice(String tel, HashMap<String, Object> options) {
        //sms.message.template.TransferNotice
        return this.sendMessage(tel, StringUtil.Replace(smsConfig.getMessageTemplateTransferNotice(), options, "{", "}"), null);
    }

}
