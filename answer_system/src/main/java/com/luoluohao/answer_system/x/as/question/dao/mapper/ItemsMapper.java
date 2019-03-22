package com.luoluohao.answer_system.x.as.question.dao.mapper;

import com.luoluohao.answer_system.web.mvc.CommonMapper;
import com.luoluohao.answer_system.x.as.question.dao.IItemsDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Items;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository("itemsDao")
public interface ItemsMapper extends IItemsDao, CommonMapper<Items>, MySqlMapper<Items> {
}