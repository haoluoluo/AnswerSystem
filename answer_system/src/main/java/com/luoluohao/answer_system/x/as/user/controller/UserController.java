package com.luoluohao.answer_system.x.as.user.controller;

import com.luoluohao.answer_system.util.MD5;
import com.luoluohao.answer_system.util.UUIDUtil;
import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.as.ems.controller.SenderController;
import com.luoluohao.answer_system.x.as.user.dto.entity.UserToken;
import com.luoluohao.answer_system.x.as.user.service.IUserService;
import com.luoluohao.answer_system.x.as.user.dto.entity.User;
import com.luoluohao.answer_system.x.as.user.service.IUserTokenService;
import com.luoluohao.answer_system.x.asUtils.EMailUtil;
import com.luoluohao.answer_system.x.asUtils.ProjectUtil;
import com.luoluohao.answer_system.x.asconfig.yml.Email;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private SenderController senderController;

    @Autowired
    private IUserTokenService userTokenService;

    @Autowired
    private Email email;

    @Value("${sys.resetPasswordUrl}")
    private String resetPasswordUrl;
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
    @PostMapping("/login")
    @ResponseBody
    public ViewJSON<Object> login(
            @RequestParam String account,
            @RequestParam String pwd

    ) {
        logger.debug("user-email="+account+"/npwd="+pwd);
        User user = userService.searchUserByEmail(account);
        if(user == null){
            return new ViewJSON<>("400", "账户不存在");
        }
        String userPwd = user.getPassword();
        if(!userPwd.equals(pwd)){
           return new ViewJSON<>("400", "密码错误");
        }
        user.setPassword("");
        return new ViewJSON<>("200", "登陆成功",user);
    }

    /**
     * @api {post} /customer/registerStep1 注册第一步
     * @apiDescription 注册页面第一步操作填写账号密码后发送验证码
     * @apiGroup customer
     * @apiParam {String}   account   Y  账户
     * @apiParam {String}   pwd       Y  密码
     * @apiVersion 1.0.0
     * @apiUse REQUEST_SUCCESS
     * @apiUse REQUEST_FAIL
     */
    @PostMapping("/registerStep1")
    @ResponseBody
    public ViewJSON<Object> registerStep1(@RequestParam String username ,@RequestParam String account, @RequestParam String pwd) {

//        if (userService.searchUserByEmail(account) == null) {
//            User user = new User();
//            user.setEmail(account);
//            user.setPassword(pwd);
//            user.setStatus(1);
//            boolean b = userService.addUser(user);
//            if(b){
//                return new ViewJSON<>("200", "添加成功");
//            }else {
//                return new ViewJSON("400","服务器错误,请稍后再试");
//            }

        if (userService.searchUserByEmail(account) == null) {
            //生成六位数字验证码
            String code = ProjectUtil.getInstance().getSix();

            HashMap<String, Object> param = new HashMap<>();
            param.put("code", code);

            if (senderController.sendMail(account, "【答题系统】验证码：" + code, "您的验证码为：" + code)) {
//            Boolean ret = senderController.sendMessage(account, ProjectUtil.getInstance().smsReplace(verifyCode, param), null);
//            if (ret) {
                //生成唯一码
                String uuid = UUIDUtil.getUUID();

                //相关信息存入表
                UserToken registerToken = new UserToken();
                //设置唯一码
                registerToken.setRegisterUuid(uuid);
                //这时用户没有注册所以没有cid
                registerToken.setUser(0);
                //对code进行MD5加密成为一个token
                registerToken.setToken(new MD5().encrypt(code, null));
                //token标记为注册使用
                registerToken.setUseageType(2);
                //token状态标记为未使用
                registerToken.setState(1);
                Date date = new Date();
                registerToken.setCreateTime(date);
                registerToken.setUpdateTime(date);
                registerToken.setDeleted(false);
                //存入账户信息
                registerToken.setProperty(username + "," + account + "," + pwd);
                userTokenService.insertThridToken(registerToken);
                return new ViewJSON<>("200", "验证码下发成功", "", "", uuid);
            } else {
                return new ViewJSON<>("400", "验证码下发失败");
            }
        } else {
            return new ViewJSON<>("400", "账户已存在");
        }
    }


    /**
     * @api {get} /customer/registerStep2 注册第二步
     * @apiDescription 注册页面第二步填写验证码完成注册
     * @apiGroup customer
     * @apiParam {String}   code   Y  验证码
     * @apiVersion 1.0.0
     * @apiUse REQUEST_SUCCESS
     * @apiUse REQUEST_FAIL
     * @apiUse Customer
     */
    @GetMapping("/registerStep2")
    @ResponseBody
    public ViewJSON<Object> registerStep2(@RequestParam String code, @RequestParam String uuid) {

        if (TextUtils.isEmpty(code)) {
            return new ViewJSON<>("400", "验证码不能为空");
        }

        String inputToken = new MD5().encrypt(code, null);
        //查询注册类的token
        UserToken token = userTokenService.selectRegisterToken(inputToken, uuid);
        if (token != null) {
            try {
                String[] infos = token.getProperty().split(",");
                if (infos.length == 3) {
                    User customer = userService.register(infos[0], infos[1], infos[2]);
                    if (customer != null) {
                        customer.setPassword("");
                        //让这一个token失效s
                        token.setState(2);
                        token.setDeleted(true);
                        userTokenService.updataTokenStateInvalid(token.getUser());
                        return new ViewJSON<>("200", "注册成功", customer);
                    } else {
                        return new ViewJSON<>("400", "注册失败", "", "写入数据库失败");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ViewJSON<>("400", "验证码已失效，请重新发送");
    }
/**
     * 发送邮件
     *
     * @param account 账户
     * @return 是否发送成功
     */
    public boolean sendEmail(String account, String title, String content) {
        try {
            EMailUtil.Send(email, account, title, content);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/findPwdStep1")
    @ResponseBody
    public ViewJSON<Object> findPwdStep1(@RequestParam Integer type, @RequestParam String account) {

//        User customer = userService.searchCustomeByType(account, type);
        User customer = userService.searchUserByEmail(account);

        if (customer == null) {
            return new ViewJSON<>("400", "账户不存在");
        }

        //发送验证码前，先要刷新一次，使之前的token全部失效
        long count = userTokenService.updataTokenStateInvalid(customer.getUserno());
        logger.debug("更新" + customer.getUsername() + "token失效" + count + "条");

        //生成一个六位数字密码
        String code = ProjectUtil.getInstance().getSix();
        //对密码进行md5，得到一个token
        String token = new MD5().encrypt(code, null);

        //token写入数据库
        if (!userTokenService.insertToken(customer.getUserno(), token)) {
            return new ViewJSON<>("400", "token写入失败");
        }

        //https://xnt.beigebigdata.com/zt-web/#/ilogin/resetpassword

        //发送短信
//        if (type == 10) {
//            HashMap<String, Object> param = new HashMap<>();
//            param.put("code", code);
//            //StringUtil.Replace(verifyCode, param, "{", "}")
//            Boolean ret = senderController.sendMessage(account, ProjectUtil.getInstance().smsReplace(findCode, param), null);
//            if (ret) {
//                return new ViewJSON<>("200", token);
//            } else {
//                return new ViewJSON<>("400", "短信发送失败");
//            }
//        }

        //开始发送邮件
        if (!sendEmail(account, "【答题系统】密码找回", resetPasswordUrl + token)) {
            return new ViewJSON<>("400", "邮件发送失败");
        }

        return new ViewJSON<>("200", "邮件发送成功");
    }


    /**
     * @api {post} /customer/findPwdStep2 找回密码第二步
     * @apiDescription 找回密码第二步，修改密码
     * @apiGroup customer
     * @apiParam {String}   token Y token
     * @apiParam {String}   pwd   Y 密码
     * @apiVersion 1.0.0
     * @apiUse REQUEST_SUCCESS
     * @apiUse REQUEST_FAIL
     */
    @PostMapping("/findPwdStep2")
    @ResponseBody
    public ViewJSON<Object> findPwdStep2(
            @RequestParam String token,
            @RequestParam String pwd
    ) {

        Integer customeId = userTokenService.validToken(token);

        //验证token是否可用
        if (customeId == null) {
            return new ViewJSON<>("400", "链接已失效，请重新发送");
        }

        User customer = userService.selectByPrimaryKey(customeId);
        //检查用户是否存在
        if (customer == null) {
            return new ViewJSON<>("400", "链接已失效，请重新发送");
        }

        //用户存在，开始修改密码
        customer.setPassword(pwd);
        userService.updateByPrimaryKey(customer);

        //修改完密码再刷新一次
        long count = userTokenService.updataTokenStateInvalid(customer.getUserno());
        logger.debug("更新" + customer.getUsername() + "token失效" + count + "条");

        return new ViewJSON<>("200", "密码修改成功");
    }
    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON getUserById(
            @PathVariable("id") Integer id
    ){
        User user = null;
        try{

            user = userService.selectByPrimaryKey(id);
        }catch (Exception e){
            logger.debug(e.getMessage());
            e.getMessage();
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200","查询成功",user);
    }
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON getAllUser(){
         List<User> users = null;
        try{

           users = userService.selectAll();
        }catch (Exception e){
            logger.debug(e.getMessage());
            e.getMessage();
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON("200","查询成功",users);
    }

    /**
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public ViewJSON updateUserInfo(
            @RequestBody User user
    ){
        try{
            User user1 = userService.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ViewJSON("400","服务器错误");
        }
        return new ViewJSON("200", "修改成功");
    }

    /**
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addUserInfo", method = RequestMethod.PUT)
    public ViewJSON addUserInfo(
            @RequestBody User user
    ){

        boolean res = false;
        try{

            res = userService.addUser(user);
        }catch (Exception e){
            logger.debug(e.getMessage());
            System.out.println(e.getMessage());
            return new ViewJSON("400","服务器错误");
        }
        if(!res){
            return new ViewJSON("400","添加失败");
        }
        return new ViewJSON("200", "修改成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
    public ViewJSON addUserInfo(
            @RequestParam(value = "id") Integer id
    ){

        int i = 0;
        try{

            i = userService.deleteByPrimaryKey(id);
        }catch (Exception e){
            return new ViewJSON("400","服务器错误");
        }
        if(1<=0){
            return new ViewJSON("400","删除失败");
        }
        return new ViewJSON("200", "修改成功");
    }
    public User selectUserById(Integer user){
        User user1 = userService.selectByPrimaryKey(user);
        return user1;
    }
}
