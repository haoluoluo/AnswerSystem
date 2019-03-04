package com.luoluohao.answer_system.x.user.service;

import com.luoluohao.answer_system.x.user.dto.entity.User;

import java.util.List;

public interface IUserService {

    User searchUserByEmail(String account);
}
