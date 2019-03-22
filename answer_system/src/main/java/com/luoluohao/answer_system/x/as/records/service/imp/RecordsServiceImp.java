package com.luoluohao.answer_system.x.as.records.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.records.dao.IRecordsDao;
import com.luoluohao.answer_system.x.as.records.dto.entity.Records;
import com.luoluohao.answer_system.x.as.records.service.IRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("recordsService")
public class RecordsServiceImp extends BaseService<Records, Integer> implements IRecordsService {
    @Autowired
    private IRecordsDao recordsDao;
    @Override
    public IBaseDao getBaseDao() {
        return recordsDao;
    }

    @Override
    public List<Records> selectByUserno(Integer userno) {
        Example example = new Example(Records.class);
        example.createCriteria().andEqualTo("user",userno);
        List<Records> recordsList = recordsDao.selectByExample(example);
        return recordsList;
    }

    @Override
    public List<Records> selectByExam(Integer examination) {
        Example example = new Example(Records.class);
        example.createCriteria().andEqualTo("examination",examination);
        List<Records> recordsList = recordsDao.selectByExample(example);
        return recordsList;
    }
}
