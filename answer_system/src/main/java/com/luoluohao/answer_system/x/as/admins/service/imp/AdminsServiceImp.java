package com.luoluohao.answer_system.x.as.admins.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.admins.dao.IAdminsDao;
import com.luoluohao.answer_system.x.as.admins.dto.entity.Admins;
import com.luoluohao.answer_system.x.as.admins.service.IAdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("adminsService")
public class AdminsServiceImp extends BaseService<Admins, Integer> implements IAdminsService {
    @Autowired
    private IAdminsDao adminsDao;
    @Override
    public IBaseDao<Admins, Integer> getBaseDao() {
        return adminsDao;
    }

    @Override
    public Admins searchUserByName(String userName) {
        Example example = new Example(Admins.class);
        example.createCriteria().andEqualTo("adminname",userName);
        List<Admins> admins = adminsDao.selectByExample(example);
        if(admins ==null || admins.size() <=0){
            return null;
        }else {
            return admins.get(0);
        }
    }
}
