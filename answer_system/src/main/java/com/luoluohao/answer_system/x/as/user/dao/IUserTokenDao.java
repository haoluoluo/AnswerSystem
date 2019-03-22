package com.luoluohao.answer_system.x.as.user.dao;

import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.user.dto.entity.UserToken;
import org.apache.ibatis.annotations.Param;

public interface IUserTokenDao extends IBaseDao<UserToken, Long> {
       long updataTokenStateInvalid(int user);
}
