package com.luoluohao.answer_system.x.as.records.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapperMySQL;
import com.luoluohao.answer_system.x.as.records.dao.IRecordsDetailDao;
import com.luoluohao.answer_system.x.as.records.dto.entity.RecordsDetail;
import org.springframework.stereotype.Repository;

@Repository("recordsDetailDao")
public interface RecordsDetailMapper extends IRecordsDetailDao, CommonMapperMySQL<RecordsDetail> {
}