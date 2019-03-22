package com.luoluohao.answer_system.x.as.admins.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.as.admins.dto.entity.Admins;
import com.luoluohao.answer_system.x.as.admins.service.IAdminsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    private IAdminsService adminsService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ViewJSON login(
            @RequestParam(value = "pwd") String pwd,
            @RequestParam(value = "userName") String userName
    ) {
        logger.debug("user-email=" + userName + "/npwd=" + pwd);
        Admins admins = adminsService.searchUserByName(userName);
        if (admins == null) {
            return new ViewJSON<>("400", "管理员不存在");
        }
        String adminsPwd = admins.getPassword();
        if (!adminsPwd.equals(pwd)) {
            return new ViewJSON<>("400", "密码不正确");
        }
        admins.setPassword("");
        return new ViewJSON<>("200", "登陆成功", admins);
    }

}
