package com.luoluohao.answer_system.x.as.question.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ExaminationView {
    private Integer id;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 试卷描述
     */
    private String describe;

    /**
     * 用时,单位(分钟)
     */
    private Integer time;

    private List<QuestionView> questions;
}
