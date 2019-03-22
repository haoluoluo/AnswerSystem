package com.luoluohao.answer_system.x.as.question.dto.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 选项名称
     */
    private String name;

    /**
     * 所属问题id
     */
    private Integer question;

    /**
     * 得分
     */
    private Integer score;

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
     * 获取选项名称
     *
     * @return name - 选项名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置选项名称
     *
     * @param name 选项名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取所属问题id
     *
     * @return question - 所属问题id
     */
    public Integer getQuestion() {
        return question;
    }

    /**
     * 设置所属问题id
     *
     * @param question 所属问题id
     */
    public void setQuestion(Integer question) {
        this.question = question;
    }
}