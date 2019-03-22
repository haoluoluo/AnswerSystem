package com.luoluohao.answer_system.x.as.user.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.user.dao.IUserTokenDao;
import com.luoluohao.answer_system.x.as.user.dto.entity.UserToken;
import org.springframework.stereotype.Repository;

@Repository("userTokenDao")
public interface UserTokenMapper extends IUserTokenDao, CommonMapper<UserToken> {
}