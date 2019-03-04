package com.luoluohao.answer_system.x.user.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.user.dto.entity.User;
import com.luoluohao.answer_system.x.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

     /**
     * @api {get} /customer/login 登录
     * @apiDescription 客户登录
     * @apiGroup customer
     * @apiParam {String}   account   Y 账户
     * @apiParam {String}   pwd       Y 密码
     * @apiVersion 1.0.0
     * @apiUse REQUEST_SUCCESS
     * @apiUse REQUEST_FAIL
     * @apiUse Customer
     */
    @GetMapping("/login")
    @ResponseBody
    public ViewJSON<Object> login(
            @RequestParam String account,
            @RequestParam String pwd

    ) {
        logger.debug("=======================begin===============");
        User user = userService.searchUserByEmail(account);
        if(user == null){
            return new ViewJSON<>("400", "账户不存在");
        }

        String userPwd = user.getPassword();
        if(!userPwd.equals(pwd)){
           return new ViewJSON<>("400", "密码错误");
        }
        return new ViewJSON<>("200", "登陆成功");
    }
}
