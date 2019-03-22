package com.luoluohao.answer_system.x.as.user.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.user.dao.IUserDao;
import com.luoluohao.answer_system.x.as.user.dto.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserMapper extends IUserDao, CommonMapper<User> {
}