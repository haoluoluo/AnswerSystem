package com.luoluohao.answer_system.x.as.question.dto.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 提交考试结果form
 */
@Setter
@Getter
public class SubmitAns {

    private Integer examId;

    private Integer user;

    private String username;

    private List<AnsView> answer;

}
