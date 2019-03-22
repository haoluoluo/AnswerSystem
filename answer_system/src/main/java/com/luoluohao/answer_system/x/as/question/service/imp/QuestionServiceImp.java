package com.luoluohao.answer_system.x.as.question.service.imp;

import com.luoluohao.answer_system.util.UUIDUtil;
import com.luoluohao.answer_system.web.mvc.BaseService;
import com.luoluohao.answer_system.web.mvc.IBaseDao;
import com.luoluohao.answer_system.x.as.question.dao.IItemsDao;
import com.luoluohao.answer_system.x.as.question.dao.IQuestionDao;
import com.luoluohao.answer_system.x.as.question.dto.entity.Items;
import com.luoluohao.answer_system.x.as.question.dto.entity.Question;
import com.luoluohao.answer_system.x.as.question.dto.model.AnsModel;
import com.luoluohao.answer_system.x.as.question.dto.model.QuestionExt;
import com.luoluohao.answer_system.x.as.question.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("questionService")
public class QuestionServiceImp extends BaseService<Question, Integer> implements IQuestionService {
    @Autowired
    private IQuestionDao questionDao;

    @Autowired
    private IItemsDao itemsDao;
    //答案读取缓存
    static private Map<Integer, AnsModel> ANSWER = new HashMap<>();

    @Override
    public Map<Integer, AnsModel> getAns(Integer examid) {
        if (null==ANSWER || 0>=ANSWER.size()) {
            //延迟加载配置
            List<Question> list = this.getQuestionByExam(examid);//this.fieldNameDao.selectAllFieldNames();
            for ( Question m : list ) {
                AnsModel ansModel = new AnsModel();
                ansModel.setScore(m.getScore());
                ansModel.setAnswer(m.getAnswer());
                ANSWER.put(m.getId(), ansModel);
            }
        }
        return ANSWER;
    }
    @Override
    public List<Question> getQuestionByExam(Integer id) {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("examination", id);
        return questionDao.selectByExample(example);
    }

    @Override
    public List<QuestionExt> getQuestionByExamWithItems(Integer id) {
//        List<QuestionExt> questionExts = new ArrayList<>();
//        List<Question> questionByExam = this.getQuestionByExam(id);
//        for(Question q : questionByExam){
//            QuestionExt questionExt = new QuestionExt();
//            BeanUtils.copyProperties(q,questionExt);
//            Example example = new Example(Items.class);
//            example.createCriteria().andEqualTo("question",q.getId());
//            List<Items> items = itemsDao.selectByExample(example);
//            questionExt.setItems(items);
//            questionExts.add(questionExt);
//        }
        List<QuestionExt> questionExts = questionDao.selectQuestionsByExamHIGH(id);
        return questionExts;
    }

    @Transactional
    @Override
    public boolean updateQuestion(QuestionExt questionExt) {
        if(questionExt.getExamination()<=0){
            return false;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionExt,question);
        int i = questionDao.updateByPrimaryKeySelective(question);
        if(i != 1){
             return false;
        }
        //如果是单选或者是多选题
        if(questionExt.getType().equals(1) || questionExt.getType().equals(2)){
            int j = 0;
            for(Items item : questionExt.getItems()){
                j += itemsDao.updateByPrimaryKeySelective(item);
            }
            if(j != questionExt.getItems().size()){
                return false;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public boolean addQuestion(QuestionExt questionExt) {
        Question question = new Question();
        String uuid = UUIDUtil.getUUID();
        BeanUtils.copyProperties(questionExt,question);
        question.setToken(uuid);
        question.setExamination(questionExt.getExamination());
        int i = questionDao.insert(question);
        if(i != 1){
            return false;
        }
        //如果是单选或者是多选题
        if(questionExt.getType().equals(1) || questionExt.getType().equals(2)) {
            try {
                int id = this.getQueIdWithToken(uuid);
                int score = 1;
                for (Items item : questionExt.getItems()) {
                    item.setQuestion(id);
                    item.setScore(score);
                    score *=2;
                }
                int j = itemsDao.insertList(questionExt.getItems());
                if(j != questionExt.getItems().size()){
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public int removeQuestion(QuestionExt questionExt) {
        int i = questionDao.deleteByPrimaryKey(questionExt.getId());
        int j = 0;
        for(Items item : questionExt.getItems()){
            j += itemsDao.deleteByPrimaryKey(item.getId());
        }
        if(i>0&&j>0){
            i = i + j;
        }else{
            i = 0;
        }
        return i;
    }
    private int getQueIdWithToken(String uuid) throws Exception {
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("token",uuid);
        List<Question> questions = questionDao.selectByExample(example);
        if(questions == null || questions.size()<=0){
            throw new Exception("Question token not exist");
        }
        return questions.get(0).getId();
    }
    @Override
    public IBaseDao<Question, Integer> getBaseDao() {
        return questionDao;
    }


}
