package com.luoluohao.answer_system.x.as.question.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class QuestionView {
    private Integer id;

    private String title;

    private Integer score;

    /**
     * 所属类型
     */
    private Integer type;

    /**
     * 所属类型解释
     */
    private String typeparse;

    private List<ItemsView> items;

}

