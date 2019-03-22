package com.luoluohao.answer_system.x.as.records.dto.entity;

import javax.persistence.*;

@Table(name = "recordsDetail")
public class RecordsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属问题
     */
    private Integer question;

    /**
     * 用户的回答
     */
    private String useranswer;

    /**
     * 正确答案
     */
    private String rightanswer;

    /**
     * 是否正确,0为正确非零为错误
     */
    private Integer isright;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所属问题
     *
     * @return question - 所属问题
     */
    public Integer getQuestion() {
        return question;
    }

    /**
     * 设置所属问题
     *
     * @param question 所属问题
     */
    public void setQuestion(Integer question) {
        this.question = question;
    }

    /**
     * 获取用户的回答
     *
     * @return useranswer - 用户的回答
     */
    public String getUseranswer() {
        return useranswer;
    }

    /**
     * 设置用户的回答
     *
     * @param useranswer 用户的回答
     */
    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

    /**
     * 获取正确答案
     *
     * @return rightanswer - 正确答案
     */
    public String getRightanswer() {
        return rightanswer;
    }

    /**
     * 设置正确答案
     *
     * @param rightanswer 正确答案
     */
    public void setRightanswer(String rightanswer) {
        this.rightanswer = rightanswer;
    }

    /**
     * 获取是否正确,0为正确非零为错误
     *
     * @return isright - 是否正确,0为正确非零为错误
     */
    public Integer getIsright() {
        return isright;
    }

    /**
     * 设置是否正确,0为正确非零为错误
     *
     * @param isright 是否正确,0为正确非零为错误
     */
    public void setIsright(Integer isright) {
        this.isright = isright;
    }
}