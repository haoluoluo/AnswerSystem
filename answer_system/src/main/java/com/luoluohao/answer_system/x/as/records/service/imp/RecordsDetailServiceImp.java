package com.luoluohao.answer_system.x.as.records.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.records.dao.IRecordsDetailDao;
import com.luoluohao.answer_system.x.as.records.dto.entity.RecordsDetail;
import com.luoluohao.answer_system.x.as.records.service.IRecordsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recordsDetailService")
public class RecordsDetailServiceImp extends BaseService<RecordsDetail, String> implements IRecordsDetailService {
    @Autowired
    private IRecordsDetailDao recordsDetailDao;
    @Override
    public IBaseDao<RecordsDetail, String> getBaseDao() {
        return recordsDetailDao;
    }
}
