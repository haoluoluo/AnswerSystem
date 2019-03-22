package com.luoluohao.answer_system.x.as.question.service.imp;

import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.question.dao.IItemsDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Items;
import com.luoluohao.answer_system.x.as.question.service.IItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("itemsService")
public class ItemsServiceImp extends BaseService<Items, Integer> implements IItemsService {
    @Autowired
    private IItemsDao itemsDao;

    @Override
    public List<Items> getItemsByQue(Integer id) {
        Example example = new Example(Items.class);
        example.createCriteria().andEqualTo("question", id);
        return itemsDao.selectByExample(example);
    }

    @Override
    public IBaseDao<Items, Integer> getBaseDao() {
        return itemsDao;
    }
}
