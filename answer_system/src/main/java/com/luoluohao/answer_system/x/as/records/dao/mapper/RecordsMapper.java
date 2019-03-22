package com.luoluohao.answer_system.x.as.records.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.records.dao.IRecordsDao;
import com.luoluohao.answer_system.x.as.records.dto.entity.Records;
import org.springframework.stereotype.Repository;

@Repository("recordsDao")
public interface RecordsMapper extends IRecordsDao, CommonMapper<Records> {
}