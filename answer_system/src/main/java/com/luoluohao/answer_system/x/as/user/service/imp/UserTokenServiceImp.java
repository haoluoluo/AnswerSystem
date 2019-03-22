package com.luoluohao.answer_system.x.as.user.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.user.dao.IUserTokenDao;
import com.luoluohao.answer_system.x.as.user.dto.entity.UserToken;
import com.luoluohao.answer_system.x.as.user.service.IUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("userTokenService")
public class UserTokenServiceImp extends BaseService<UserToken, Long> implements IUserTokenService {

    @Autowired
    private IUserTokenDao userTokenDao;

    @Override
    public IBaseDao<UserToken, Long> getBaseDao() {
        return userTokenDao;
    }

    @Override
    public boolean insertThridToken(UserToken token) {
        return userTokenDao.insertSelective(token) == 1;
    }
    @Override
    public UserToken selectRegisterToken(String token, String uuid) {
        Example example = new Example(UserToken.class);
        example.createCriteria()
                .andEqualTo("state", 1)
                .andEqualTo("useageType", 2)
                .andEqualTo("token", token)
                .andEqualTo("registerUuid", uuid);
        List<UserToken> list = userTokenDao.selectByExample(example);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public long updataTokenStateInvalid(Integer user) {
        return userTokenDao.updataTokenStateInvalid(user);
    }
    @Override
    public boolean insertToken(Integer userno, String token) {
        UserToken customerToken = new UserToken();
        customerToken.setUser(userno);
        customerToken.setState(1);
        customerToken.setUseageType(1);
        customerToken.setToken(token);

        Date today = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        customerToken.setCreateTime(today);
        customerToken.setUpdateTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();
        customerToken.setExpireTime(tomorrow);

        return userTokenDao.insertSelective(customerToken) == 1;
    }
    @Override
    public Integer validToken(String token) {
        Example example = new Example(UserToken.class);
        example.createCriteria()
                .andEqualTo("state", 1)
                .andEqualTo("token", token);
        List<UserToken> list = userTokenDao.selectByExample(example);
        if (list != null && list.size() == 1) {
            return list.get(0).getUser();
        }
        return null;
    }
}
