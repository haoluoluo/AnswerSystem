package com.luoluohao.answer_system.x.as.question.dto.model;

import com.luoluohao.answer_system.x.as.question.dto.entity.Examination;

import java.util.List;

public class ExaminationExt extends Examination {
    public List<QuestionExt> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionExt> questions) {
        this.questions = questions;
    }

    private List<QuestionExt> questions;
}
