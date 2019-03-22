package com.luoluohao.answer_system.x.as.sys.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.sys.dao.IFieldNameDao;
import com.luoluohao.answer_system.x.as.sys.dto.entity.FieldName;
import org.springframework.stereotype.Repository;

@Repository("fieldNameDao")
public interface FieldNameMapper extends IFieldNameDao, CommonMapper<FieldName> {
}