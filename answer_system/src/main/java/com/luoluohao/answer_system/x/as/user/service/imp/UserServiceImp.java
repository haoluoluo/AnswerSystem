package com.luoluohao.answer_system.x.as.user.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.user.dao.IUserDao;
import com.luoluohao.answer_system.x.as.user.dto.entity.User;
import com.luoluohao.answer_system.x.as.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("userService")
public class UserServiceImp extends BaseService<User, Integer> implements IUserService {
   Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserDao userDao;
    @Override
    public IBaseDao getBaseDao() {
        return userDao;
    }


    @Override
    public User searchUserByEmail(String account) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email",account);
        List<User> users = userDao.selectByExample(example);
        if(users==null || users.size()<=0){
           return null;
        }
        return users.get(0);
    }

    @Override
    public boolean addUser(User user) {
        int insert = userDao.insert(user);
        if(insert <= 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public User register(String username, String account, String pwd) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(account);
            user.setPassword(pwd);
            user.setStatus(1);
        int insert = userDao.insert(user);
        if(insert == 1)
            return user;
        else
            return null;
    }
}
