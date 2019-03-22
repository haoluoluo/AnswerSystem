package com.luoluohao.answer_system.x.as.records.dto.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "records")
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属用户
     */
    private Integer user;

    /**
     * 正确数量
     */
    private Integer acnumber;

    /**
     * 错误数量
     */
    private Integer ernumber;

    /**
     * 得分
     */
    private Integer scope;

    /**
     * 所属试卷
     */
    private Integer examination;

    /**
     * 试卷名称
     */
    @Column(name = "examinationName")
    private String examinationName;


    /**
     *
      */
    private String username;

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
     * 获取所属用户
     *
     * @return user - 所属用户
     */
    public Integer getUser() {
        return user;
    }

    /**
     * 设置所属用户
     *
     * @param user 所属用户
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * 获取正确数量
     *
     * @return acnumber - 正确数量
     */
    public Integer getAcnumber() {
        return acnumber;
    }

    /**
     * 设置正确数量
     *
     * @param acnumber 正确数量
     */
    public void setAcnumber(Integer acnumber) {
        this.acnumber = acnumber;
    }

    /**
     * 获取错误数量
     *
     * @return ernumber - 错误数量
     */
    public Integer getErnumber() {
        return ernumber;
    }

    /**
     * 设置错误数量
     *
     * @param ernumber 错误数量
     */
    public void setErnumber(Integer ernumber) {
        this.ernumber = ernumber;
    }

    /**
     * 获取得分
     *
     * @return scope - 得分
     */
    public Integer getScope() {
        return scope;
    }

    /**
     * 设置得分
     *
     * @param scope 得分
     */
    public void setScope(Integer scope) {
        this.scope = scope;
    }

    /**
     * 获取所属试卷
     *
     * @return examination - 所属试卷
     */
    public Integer getExamination() {
        return examination;
    }

    /**
     * 设置所属试卷
     *
     * @param examination 所属试卷
     */
    public void setExamination(Integer examination) {
        this.examination = examination;
    }
}