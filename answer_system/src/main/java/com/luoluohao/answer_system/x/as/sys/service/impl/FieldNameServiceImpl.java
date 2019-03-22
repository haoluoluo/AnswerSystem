package com.luoluohao.answer_system.x.as.sys.service.impl;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.sys.dao.IFieldNameDao;
import com.luoluohao.answer_system.x.as.sys.service.IFieldNameService;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldName;
import com.luoluohao.answer_system.x.as.sys.dto.model.FieldNameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Filename: com.abcdpower.x.iwill.sys.service.impl.FieldNameServiceImpl.java
 * @Type: FieldNameServiceImpl
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/21 上午12:51
 * @Version: iwill.zt-service V1.0.0
 */

@Service("fieldNameService")
public class FieldNameServiceImpl extends BaseService<FieldName, Integer> implements IFieldNameService {

    //枚举配置读取缓存
    static private Map<String, FieldNameModel> FieldNames = new HashMap<>();

    @Autowired
    private IFieldNameDao fieldNameDao;

    @Override
    public IBaseDao<FieldName, Integer> getBaseDao() {
        // TODO Auto-generated method stub
        return fieldNameDao;
    }

    public FieldNameModel getFieldName(String name) {
        if (null==FieldNames || 0>=FieldNames.size()) {
            //延迟加载配置
            List<FieldNameModel> list = this.fieldNameDao.selectAllFieldNamesHIGH();//this.fieldNameDao.selectAllFieldNames();
            for ( FieldNameModel m : list ) FieldNames.put(m.getName(),m);
        }

        //读取配置缓存，因此，配置不会即时生效。
        FieldNameModel fieldName = FieldNames.get(name);
        if (null!=fieldName) return fieldName;

        //无相关配置（截止服务启动前的配置才会有效，否则需要重新启动服务）
        return null;
    }
}
