package com.luoluohao.answer_system.x.as.user.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.user.dto.entity.UserToken;

public interface IUserTokenService extends IBaseService<UserToken, Long> {

    boolean insertThridToken(UserToken token);

    UserToken selectRegisterToken(String token, String uuid);

    long updataTokenStateInvalid(Integer customerId);

    boolean insertToken(Integer userno, String token);

    Integer validToken(String token);
}
