package com.luoluohao.answer_system.x.as.admins.service;

import com.luoluohao.answer_system.web.mvc.IBaseService;
import com.luoluohao.answer_system.x.as.admins.dto.entity.Admins;

public interface IAdminsService extends IBaseService<Admins, Integer> {
    Admins searchUserByName(String userName);
}
