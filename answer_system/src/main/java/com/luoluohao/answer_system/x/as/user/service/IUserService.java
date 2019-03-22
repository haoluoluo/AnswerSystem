package com.luoluohao.answer_system.x.as.user.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.user.dto.entity.User;

public interface IUserService extends IBaseService<User, Integer> {

    User searchUserByEmail(String account);

    boolean addUser(User user);

    User register(String username, String account, String pwd);
}
