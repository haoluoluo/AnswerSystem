package com.luoluohao.answer_system.x.as.question.dto.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnsView {
    /**
     *问题id
     */
    private Integer question;
    /**
     * 用户回答
     */
    private String answer;
}
