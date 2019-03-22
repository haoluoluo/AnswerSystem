package com.luoluohao.answer_system.x.as.question.dto.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "examination")
public class Examination implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 试卷描述
     */
    @Column(name = "des")
    private String describe;

    /**
     * 用时,单位(分钟)
     */
    private Integer time;

    private Integer status;

    /**
     * 试卷所属类型
     */
    private Integer type;

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
     * 获取试卷名称
     *
     * @return name - 试卷名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置试卷名称
     *
     * @param name 试卷名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取试卷描述
     *
     * @return describe - 试卷描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置试卷描述
     *
     * @param describe 试卷描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取用时,单位(分钟)
     *
     * @return time - 用时,单位(分钟)
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置用时,单位(分钟)
     *
     * @param time 用时,单位(分钟)
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}