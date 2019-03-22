package com.luoluohao.answer_system.x.as.sms.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.asUtils.HTTPUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信发送报告控制
 *
 * @Filename: com.abcdpower.x.iwill.sms.controller.ReportController.java
 * @Type: ReportController
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/30 下午4:49
 * @Version: iwill.zt-service V1.0.0
 */
@RestController(value = "smsReportController")
@EnableAutoConfiguration
@RequestMapping("/sms/report")
public class ReportController {

    /**
     * @Description 短信发送报告默认回调入口
     *
     * @Param [json]
     * @Return com.abcdpower.util.ViewJSON<java.lang.Object>
     */
    @ResponseBody
    @RequestMapping(value="/default", method= RequestMethod.GET)
    public ViewJSON<Object> reportDefaultCallback(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(HTTPUtil.ParseRequest(request));

        return new ViewJSON<>();
    }
}
