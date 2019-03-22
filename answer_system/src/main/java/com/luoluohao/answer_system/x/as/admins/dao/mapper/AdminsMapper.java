package com.luoluohao.answer_system.x.as.admins.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.admins.dao.IAdminsDao;
import com.luoluohao.answer_system.x.as.admins.dto.entity.Admins;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository("adminsDao")
public interface AdminsMapper extends IAdminsDao, CommonMapper<Admins> {
}