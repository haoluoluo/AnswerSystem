package com.luoluohao.answer_system.x.as.sys.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.sys.dao.IFieldValueDao;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldValue;
import org.springframework.stereotype.Repository;

@Repository("fieldValueDao")
public interface FieldValueMapper extends IFieldValueDao, CommonMapper<FieldValue> {
}